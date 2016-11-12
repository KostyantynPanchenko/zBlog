/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.web.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Web MVC java-based configuration. Here all necessary beans are announced and
 * static resources (like css or html pages) handling is configured.
 *  
 * @author 	Kostyantyn Panchenko
 * @version 1.2
 * @since 	09.08.2016
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("yougetit.web.controller")
public class WebAppConfig extends WebMvcConfigurerAdapter {
	private String prefix = "/WEB-INF/views/";
	private String suffix = ".jsp";	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/WEB-INF/resources/");
	}
	
	/**
	 * Configures ViewRosolver.
	 * @return configured ViewResolver instance. 
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix(prefix);
		resolver.setSuffix(suffix);
		resolver.setOrder(1);
		return resolver;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
	
	/**
	 * Configures MessageSource.
	 * @return configured MEsageSource instance.
	 */
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = 
	        new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames(new String[] {"config/labels", "config/messages"});
	    messageSource.setCacheSeconds(10);
	    return messageSource;
	}
	
	/**
	 * Configures LocaleResolver.
	 * @return configured LocaleResolver instance.
	 */
	@Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("langCookie");
        resolver.setCookieMaxAge(60 * 60 * 24 * 10);
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }
	
	/**
	 * Configures LocaleChangeInterceptor.
	 * @return configures LocaleChangeInterceptor.
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();		
		interceptor.setParamName("lang");
		return interceptor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	/**
	 * Configures Validator.
	 * @return configured Validator instance.
	 */
	@Bean
	public Validator validator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
}
