/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.dao.UserDAO;
import yougetit.entity.BlogUser;

/**
 * Custom implementation of UserDetailsService interface.
 *  
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since	29.07.2016
 */
@Repository(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier(value = "userService")
	private UserDAO userService;
	
	/**
	 * {@inheritDoc}}
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BlogUser user = userService.getUser(username);
		
		if (user != null) {
			Set<GrantedAuthority> roles = new HashSet<>();
			roles.add(new SimpleGrantedAuthority(user.getRole().name()));
			
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		
		throw new UsernameNotFoundException("User " + username + " not found!");
	}
}
