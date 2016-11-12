/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao;

import java.util.List;

import yougetit.entity.BlogUser;
import yougetit.entity.Comment;

/**
 * Comment DAO interface.
 * @author  Kostyantyn Panchenko
 * @version 1.0
 * @since   01.08.2016
 */
public interface CommentDAO extends GenericDAO<Comment, Integer> {
	
    /**
     * Finds comment by given author.
     * @param author given author.
     * @return list of comments by given author.
     */
    public abstract List<Comment> findByAuthor(BlogUser author);
}
