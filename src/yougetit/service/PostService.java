/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.service;

import java.util.List;

import yougetit.entity.BlogUser;
import yougetit.entity.Post;

/**
 * Post services.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 *
 */
public interface PostService {

    /**
     * Searches post by given id.
     * @param id given post id.
     * @return post with given id.
     */
    public abstract Post findById(int id);

    /**
     * Searches post by given author.
     * @param author given author.
     * @return list of posts by given author. 
     */
    public abstract List<Post> getByAuthor(BlogUser author);

    /**
     * Finds all posts.
     * @return list of all posts.
     */
    public abstract List<Post> findAll();

    /**
     * Retrieves posts by given page namber.
     * @param pageNumber page number to search upon.
     * @return list of post by given page.
     */
    public abstract List<Post> getPostByPageNumber(int pageNumber);

    /**
     * Counts all posts.
     * @return number of posts.
     */
    public abstract int getCount();

    /**
     * Saves given post.
     * @param post post to be saved.
     */
    public abstract void save(Post post);

    /**
     * Updates given post.
     * @param post post to be updated.
     */
    public abstract void update(Post post);

    /**
     * Searches posts by given pattern.
     * @param pattern pattern to search upon.
     * @return list of post matching given pattern.
     */
    public abstract List<Post> search(String pattern);

}
