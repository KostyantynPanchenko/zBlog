package yougetit.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.data.BlogUser;
import yougetit.data.Post;
import yougetit.service.generic.AbstractDAO;
import yougetit.service.generic.UserDataAccessService;

/**
 * UserDataAccessService default implementation.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 02.008.2016
 */
@Repository
@Transactional
@Component(value = "userService")
public class UserDAO extends AbstractDAO<BlogUser, Integer> implements UserDataAccessService {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sf;
	
	public UserDAO() {
		super(BlogUser.class);
	}
		
	@SuppressWarnings("unchecked")
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

	@Override
	public void addPost(int userId, Post post) {
		BlogUser user = findById(userId);
		user.addPost(post);		
	}	
}
