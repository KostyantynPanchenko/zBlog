package yougetit.service.generic;

import java.util.List;

import yougetit.data.BlogUser;
import yougetit.data.Comment;

public interface CommentDataAccessService extends GenericDAO<Comment, Integer> {
	public abstract List<Comment> findByAuthor(BlogUser author);
}
