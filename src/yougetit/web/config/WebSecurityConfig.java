/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import yougetit.dao.impl.UserDetailsServiceImpl;

/**
 * Security configuration class. Sets permissions to access certain URLs with defined user roles.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.1
 * @since 	02.08.2016
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = {UserDetailsServiceImpl.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	/**
     * {@inheritDoc}}
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/posts/**").permitAll()
				.antMatchers(HttpMethod.POST, "/posts/**").authenticated()
		        .antMatchers("/resources/**").permitAll()
		        .antMatchers("/admin/**").access("isAuthenticated() and hasRole('ADMIN')")	
		        .and()
		    .formLogin()
		        .loginPage("/login").permitAll()
		        .and()
		    .logout()
		    	.logoutUrl("/logout")				// the URL that triggers log out to occur		    	                                      
				.logoutSuccessUrl("/")	// the URL to redirect to after logout has occurred - /login?logout
				.invalidateHttpSession(true)		// prevent forever logged in user
				.permitAll();						
	}
	
	/**
	 * Configures AuthenticationMAnager to use UserDetailsService implementations and sets password
	 * encoder.
	 * @param auth AuthenticationManagerBuilder instance.
	 * @throws Exception
	 */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {    	
		auth
        	.userDetailsService(userDetailsService)
        	.passwordEncoder(getShaPasswordEncoder());
    }

    /**
     * Configures SHA password encoder.
     * @return configured ShaPasswordEncoder instance.
     */
    @Bean
	public ShaPasswordEncoder getShaPasswordEncoder() {
		 return new ShaPasswordEncoder();
	}

}
