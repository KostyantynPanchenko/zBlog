/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.service;

import java.util.List;

import yougetit.entity.BlogUser;
import yougetit.entity.Post;

/**
 * User services.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 *
 */
public interface UserService {

    /**
     * Searches user by given id. 
     * @param id given id.
     * @return user.
     */
    public abstract BlogUser findById(int id);

    /**
     * Retrieves user by given name.
     * @param name given name.
     * @return user wit hgiven name.
     */
    public abstract BlogUser getUser(String name);

    /**
     * Finds all users.
     * @return list of all users.
     */
    public abstract List<BlogUser> findAll();

    /**
     * Adds given post to user wih given id.
     * @param id given is.
     * @param post given post.
     */
    public abstract void addPost(int id, Post post);

    /**
     * Saves given user.
     * @param newUser user to be saved.
     */
    public abstract void save(BlogUser newUser);
       

}
