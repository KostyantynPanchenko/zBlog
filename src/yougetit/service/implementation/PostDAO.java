package yougetit.service.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.data.BlogUser;
import yougetit.data.Page;
import yougetit.data.Post;
import yougetit.service.generic.AbstractDAO;
import yougetit.service.generic.PostDataAccessService;

/**
 * DAO implementation for Post class
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.1
 * @since 	13.08.2016
 */
@Repository
@Transactional
@Component(value = "postingService")
public class PostDAO extends AbstractDAO<Post, Integer> implements PostDataAccessService{
	
	public PostDAO() {
		super(Post.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getPostByPageNumber(int pageNumber) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Post.class);
		criteria.addOrder(Order.desc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult((pageNumber - 1) * Page.PAGE_SIZE);
		criteria.setMaxResults(Page.PAGE_SIZE);
		return criteria.list();
	}
	
	/**
	 * Loading requested by id post and initializing its lazy fields
	 * @param id - post's id
	 * @return
	 */
	@Override
	public Post findById(Integer id) {
		Post post = (Post) sessionFactory.getCurrentSession().get(Post.class, id);
		Hibernate.initialize(post.getComments());
		return post;
	}
	
	/**
	 * Searching by given pattern
	 * @param pattern - String pattern to search
	 * @return list of matching posts
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> search(String pattern) {		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.like("content", "%" + pattern + "%"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	/**
	 * Searching post by author
	 * @param author - BlogUser object, post's author
	 * @return list of post
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getByAuthor(BlogUser author) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("author", author));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
}
