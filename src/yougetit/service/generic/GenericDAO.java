package yougetit.service.generic;

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
	T findById(ID id);	
	List<T> findAll();
	int getCount();
	ID save(T entity);
	T update(T entity);
	void delete(T entity);
}
