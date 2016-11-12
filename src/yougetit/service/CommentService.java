/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.service;

import java.util.List;

import yougetit.entity.BlogUser;
import yougetit.entity.Comment;

/**
 * Comment services.
 * @author Kostyantyn Panchenko
 * @version 1.0
 *
 */
public interface CommentService {

    /**
     * Searches comment by given id.
     * @param id given id.
     * @return comment.
     */
    public abstract Comment findById(int id);

    /**
     * Searches comments by author.
     * @param author given author.
     * @return list of comments by given author
     */
    public abstract List<Comment> findByAuthor(BlogUser author);

    /**
     * Finds all comments.
     * @return list of all comments
     */
    public abstract List<Comment> findAll();

    /**
     * Saves given comment.
     * @param comment comment to save.
     */
    public abstract void save(Comment comment);
        

}
