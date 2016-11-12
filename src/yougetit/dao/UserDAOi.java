/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao;

import yougetit.entity.BlogUser;

/**
 * Simple interface for fetching user details.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	29.07.2016
 *
 */
public interface UserDAOi {
	BlogUser getUser(String login);
}
