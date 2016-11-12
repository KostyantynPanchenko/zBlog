package yougetit.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.dao.PostDAO;
import yougetit.entity.BlogUser;
import yougetit.entity.Page;
import yougetit.entity.Post;

/**
 * DAO implementation for Post class
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.1
 * @since 	13.08.2016
 */
@Repository(value = "postingService")
@Transactional
public class PostDAOimpl extends AbstractDAO<Post, Integer> implements PostDAO{
	
	public PostDAOimpl() {
		super(Post.class);
	}

	/**
	 * {@inheritDoc}}
	 */
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
	 * {@inheritDoc}}
	 */
	@Override
	public Post findById(Integer id) {
		Post post = (Post) sessionFactory.getCurrentSession().get(Post.class, id);
		Hibernate.initialize(post.getComments());
		return post;
	}
	
	/**
     * {@inheritDoc}}
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
     * {@inheritDoc}}
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
