package yougetit.service.generic;

import java.util.List;

import yougetit.data.BlogUser;
import yougetit.data.Post;

public interface PostDataAccessService extends GenericDAO<Post, Integer> {
	public abstract List<Post> getPostByPageNumber(int pageNumber);
	public abstract List<Post> search(String pattern);
	public abstract List<Post> getByAuthor(BlogUser author);
}
