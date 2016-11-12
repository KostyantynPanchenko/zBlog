/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yougetit.dao.PostDAO;
import yougetit.entity.BlogUser;
import yougetit.entity.Post;
import yougetit.service.PostService;

/**
 * PostService implementation.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 *
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO dao;
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public Post findById(int id) {
        return dao.findById(id);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<Post> getByAuthor(BlogUser author) {
        return dao.getByAuthor(author);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<Post> findAll() {
        return dao.findAll();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<Post> getPostByPageNumber(int pageNumber) {
        return dao.getPostByPageNumber(pageNumber);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public int getCount() {
        return dao.getCount();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void save(Post post) {
        dao.save(post);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void update(Post post) {
        dao.update(post);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<Post> search(String pattern) {
        return dao.search(pattern);
    }

}
