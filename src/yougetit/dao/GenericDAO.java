/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.dao;

import java.util.List;

/**
 * Generic data access interface. Provide common data manipulation methods.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	01.08.2016
 *
 * @param <T> - entity class
 * @param <ID> - id class
 */
public interface GenericDAO<T, ID> {
	
    /**
     * Finds entity by given id.
     * @param id given id.
     * @return entity with given id.
     */
    public abstract T findById(ID id);
    
    /**
     * Finds all entities.
     * @return list of entities.
     */
	public abstract List<T> findAll();
	
	/**
	 * Counts all records.
	 * @return number of records.
	 */
	public abstract int getCount();
	
	/**
	 * Persists given entity.
	 * @param entity given entity.
	 * @return generated id.
	 */
	public abstract ID save(T entity);
	
	/**
	 * Updates given entity.
	 * @param entity given entity.
	 * @return updated entity.
	 */
	public abstract T update(T entity);
	
	/**
	 * Deletes given entity.
	 * @param entity given entity.
	 */
	public abstract void delete(T entity);
}
