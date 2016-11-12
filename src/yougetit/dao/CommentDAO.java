package yougetit.dao;

import java.util.List;

import yougetit.entity.BlogUser;
import yougetit.entity.Comment;

public interface CommentDAO extends GenericDAO<Comment, Integer> {
	public abstract List<Comment> findByAuthor(BlogUser author);
}
