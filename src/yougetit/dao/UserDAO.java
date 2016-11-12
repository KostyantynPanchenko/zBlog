/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao;

import yougetit.entity.BlogUser;
import yougetit.entity.Post;

public interface UserDAO extends GenericDAO<BlogUser, Integer> {
	
    /**
     * Searches blog user by given login.
     * @param login user's login.
     * @return user
     */
    public abstract BlogUser getUser(String login);

    /**
     * Adds new post for given user.
     * @param userId given user's id.
     * @param post post to add.
     */
	public abstract void addPost(int userId, Post post);
}
