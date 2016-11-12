/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.dao.UserDAO;
import yougetit.entity.BlogUser;
import yougetit.entity.Post;

/**
 * UserDataAccessService default implementation.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 02.008.2016
 */
@Repository(value = "userService")
@Transactional
public class UserDAOimpl extends AbstractDAO<BlogUser, Integer> implements UserDAO {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sf;
	
	/**
	 * Default constructor.
	 */
	public UserDAOimpl() {
		super(BlogUser.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BlogUser getUser(String login) {
		List<BlogUser> users = new ArrayList<BlogUser>();
		
		users = sf.getCurrentSession()
			.createQuery("from BlogUser where username=?")
			.setParameter(0, login).list();			

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void addPost(int userId, Post post) {
		BlogUser user = findById(userId);
		user.addPost(post);		
	}	
}
