/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.dao.CommentDAO;
import yougetit.entity.BlogUser;
import yougetit.entity.Comment;

/**
 * DAO for Comment class entities.
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 01.08.2016
 */
@Repository
@Transactional
@Component(value = "commentService")
public class CommentDAOimpl extends AbstractDAO<Comment, Integer> implements CommentDAO {

    /**
     * Default constructor.
     */
	public CommentDAOimpl() {
		super(Comment.class);
	}

	/**
	 * {@inheritDoc}	
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findByAuthor(BlogUser author) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("author", author));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}	
}
