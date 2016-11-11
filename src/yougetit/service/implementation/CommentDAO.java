package yougetit.service.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.data.BlogUser;
import yougetit.data.Comment;
import yougetit.service.generic.AbstractDAO;
import yougetit.service.generic.CommentDataAccessService;

/**
 * DAO for Comment class entities.
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 01.08.2016
 */
@Repository
@Transactional
@Component(value = "commentService")
public class CommentDAO extends AbstractDAO<Comment, Integer> implements CommentDataAccessService {

	public CommentDAO() {
		super(Comment.class);
	}

	/**
	 * Searching by given author
	 * @param author - BlogUser object representing comment author
	 * @return list of matching comments
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
