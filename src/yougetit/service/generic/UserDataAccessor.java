package yougetit.service.generic;

import yougetit.data.BlogUser;

/**
 * Simple interface for fetching user details.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	29.07.2016
 *
 */
public interface UserDataAccessor {
	BlogUser getUser(String login);
}
