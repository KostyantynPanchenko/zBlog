package yougetit.service.generic;

import yougetit.data.BlogUser;
import yougetit.data.Post;

public interface UserDataAccessService extends GenericDAO<BlogUser, Integer> {
	BlogUser getUser(String login);

	void addPost(int userId, Post post);
}
