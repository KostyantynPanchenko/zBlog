package yougetit.dao;

import yougetit.entity.BlogUser;
import yougetit.entity.Post;

public interface UserDAO extends GenericDAO<BlogUser, Integer> {
	BlogUser getUser(String login);

	void addPost(int userId, Post post);
}
