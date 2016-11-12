/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yougetit.dao.UserDAO;
import yougetit.entity.BlogUser;
import yougetit.entity.Post;
import yougetit.service.UserService;

/**
 * UserService implementation.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public BlogUser findById(int id) {
        return dao.findById(id);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public BlogUser getUser(String name) {
        return dao.getUser(name);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<BlogUser> findAll() {
        return dao.findAll();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void addPost(int id, Post post) {
        dao.addPost(id, post);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void save(BlogUser newUser) {
        dao.save(newUser);
    }

}
