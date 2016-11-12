/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yougetit.dao.CommentDAO;
import yougetit.entity.BlogUser;
import yougetit.entity.Comment;
import yougetit.service.CommentService;

/**
 * CommentService implementation.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO dao;
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public Comment findById(int id) {
        return dao.findById(id);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<Comment> findByAuthor(BlogUser author) {
        return dao.findByAuthor(author);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<Comment> findAll() {
        return dao.findAll();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void save(Comment comment) {
        dao.save(comment);
    }

}
