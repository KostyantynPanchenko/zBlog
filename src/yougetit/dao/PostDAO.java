/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao;

import java.util.List;

import yougetit.entity.BlogUser;
import yougetit.entity.Post;

/**
 * Post DAO interface.
 * @author  Kostyantyn Panchenko
 * @version 1.0
 * @since   01.08.2016
 */
public interface PostDAO extends GenericDAO<Post, Integer> {
	
    /**
     * Retrieves posts by given page number.
     * @param pageNumber page number.
     * @return list of posts.
     */
    public abstract List<Post> getPostByPageNumber(int pageNumber);
	
    /**
     * Searches for given pattern.
     * @param pattern given pattern.
     * @return list of post matching given pattern.
     */
    public abstract List<Post> search(String pattern);
	
    /**
     * Searches posts by given author.
     * @param author given author.
     * @return list of posts by given author.
     */
    public abstract List<Post> getByAuthor(BlogUser author);
}
