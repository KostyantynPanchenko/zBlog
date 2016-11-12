package yougetit.dao;

import java.util.List;

import yougetit.entity.BlogUser;
import yougetit.entity.Post;

public interface PostDAO extends GenericDAO<Post, Integer> {
	public abstract List<Post> getPostByPageNumber(int pageNumber);
	public abstract List<Post> search(String pattern);
	public abstract List<Post> getByAuthor(BlogUser author);
}
