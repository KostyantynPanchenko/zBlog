package yougetit.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yougetit.dao.GenericDAO;

/**
 * Abstract class providing default implementations for data manipulation methods.
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since	01.08.2016
 *
 * @param <T> - entity class
 * @param <ID> - id class
 */
@Repository
@Transactional
public abstract class AbstractDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;
	
	protected final Class<T> entityClass;
	
	/**
	 * Generic constructor
	 */
	protected AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * Returns an entity with given id.
	 */
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		return (T) sessionFactory.getCurrentSession().get(entityClass, id);		
	}
	
	/**
	 * Returns all elements of type T
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}
	
	/**
	 * Counts number of records.
	 * @return number of records
	 */
	public int getCount() {		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		Long res = (Long) criteria.uniqueResult();		
		return res.intValue(); 
	}
		
	/**
	 * Persist the given transient instance, returning instance identifier
	 */
	@SuppressWarnings("unchecked")
	public ID save(T entity) {
		return (ID) sessionFactory.getCurrentSession().save(entity);
	}
	
	/**
	 * Persist the given transient instance, returning a copy of persisted
	 * instance
	 */
	@SuppressWarnings("unchecked")
	public T update(T entity) {
		return (T) sessionFactory.getCurrentSession().merge(entity);
	}
	
	/**
	 * Deletes given instance
	 */
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
}
