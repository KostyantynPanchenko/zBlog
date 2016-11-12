package yougetit.web.controller;

import java.util.Date;
import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import yougetit.dao.CommentDAO;
import yougetit.dao.PostDAO;
import yougetit.dao.UserDAO;
import yougetit.entity.BlogUser;
import yougetit.entity.Comment;
import yougetit.entity.Page;
import yougetit.entity.Post;

/**
 * This class handles requests addressed to main page.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	29.07.2016
 */
@Controller
public class CoreController {
	
	@Autowired
	private PostDAO postingService;
	
	@Autowired
	private UserDAO userService;
	
	@Autowired
	private CommentDAO commentService;
	
	/**
	 * Handles requests to main page
	 * @return - Model object
	 */	
	@GetMapping(value = "/")
	public ModelAndView showIndex() {
		return showPageByNumber(1);
	}
	
	/**
	 * Handles pagination
	 * @param pageNumber - number of page to be returned
	 * @return requested page
	 */
	@GetMapping(value = "/page{pageNumber}")
	public ModelAndView showPageByNumber(@PathVariable("pageNumber") int pageNumber ) {
		Page page = null;
		
//		if ((page = cache.get(pageNumber)) != null) {
//			return new ModelAndView("page", "page", page);
//		} else {
			List<Post> posts = postingService.getPostByPageNumber(pageNumber);
			page = new Page(pageNumber);
			page.setPosts(posts);
			setTotalPages(page);			
//			cache.put(pageNumber, page);			
			return new ModelAndView("page", "page", page);
//		}		
	}
	
	/**
	 * Sets total number of pages to Page object
	 * @param page - Page object
	 */
	private void setTotalPages(Page page) {
		int totalRecords = postingService.getCount();		
				
		if ((totalRecords % Page.PAGE_SIZE) > 0) {
			page.setPagesTotal((totalRecords / Page.PAGE_SIZE) + 1);			
		} else {
			page.setPagesTotal((totalRecords / Page.PAGE_SIZE));			
		}
	}
	
	/**
	 * Handles requests to Contact page
	 * @return
	 */
	@GetMapping(value = "/contact")
	public String showContactPage() {
		return "contact";
	}
	
	/**
	 * Handling request to adding new post
	 */
	@GetMapping(value = "/posts/add")
	public String showAddNewPostPage(Model model) {
		model.addAttribute(new Post());
		return "addPost";
	}
	
	/**
	 * Processing adding new post
	 * @return
	 */
	@PostMapping(value = "/posts/add")
	public ModelAndView addNewPost(HttpServletRequest request) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = request.getParameter("author");		
		
		ModelAndView model = new ModelAndView();
		if (title.trim() == "") {
			model.addObject("emptyTitle", "empty");
			if (content.trim() == "") {
				model.addObject("emptyContent", "empty");
			}
			model.setViewName("addPost");
			return model;
		}
		
		BlogUser user = userService.getUser(author);
		
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		post.setAuthor(user);
		postingService.save(post);
				
		userService.addPost(user.getId(), post);
		model.setViewName("redirect:/");
		return model;
	}
	
	/**
	 * Displays post by id
	 * @param id - post id
	 * @return ModelAndView for displaying given post by id
	 */
	@GetMapping(value = "/posts/{id}")
	public ModelAndView showPostById(@PathVariable("id") int id) {
		Post post = postingService.findById(id); 		
		return new ModelAndView("post", "thePost", post);
	}
	
	/**
	 * Handles post edition
	 * @param id - post id
	 * @return ModelAndView object
	 */
	@GetMapping(value = "/posts/{id}/edit")
	public ModelAndView showEdit(@PathVariable("id") int id) {
		Post post = postingService.findById(id);
		return new ModelAndView("editPost", "thePost", post);
	}

	/**
	 * Handles post editing
	 * @param id - post id
	 * @return ModelAndView object
	 */
	@PostMapping(value = "/posts/{id}/edit")
	public String doEdit(@PathVariable("id") int id, HttpServletRequest request) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");				
		
		Post post = postingService.findById(id);
		post.setTitle(title);
		post.setContent(content);
		post.setLastModified(new Date());
		postingService.update(post);		

		return "redirect:/posts/{id}";
	}
	
	/**
	 * Adding a new comment to a post
	 * @param id - post id
	 * @param request - HttpServletRequest
	 * @return page with added post
	 */
	@PostMapping(value = "/posts/{id}/comment")
	public ModelAndView addComment(@PathVariable("id") int id, HttpServletRequest request) {
		String text = request.getParameter("commentText");
		String login = request.getParameter("authorName");
				
		if (text.trim() == "") {
			ModelAndView model = new ModelAndView("post");
			Post post = postingService.findById(id);
			model.addObject("thePost", post);
			model.addObject("emptyComment", "empty");
			return model;
		}
		
		BlogUser author = userService.getUser(login);
		Post post = postingService.findById(id);
		
		Comment comment = new Comment();
		comment.setContent(text);		
		comment.setAuthor(author);		
		comment.setPost(post);
		commentService.save(comment);
		
		post.addComment(comment);			

		return new ModelAndView("redirect:/posts/" + id);
	}
	
	/**
	 * Deletes post with given id
	 * @param id - post id
	 * @return
	 */
	@PostMapping(value = "/posts/{id}/delete")
	public String deletePost(@PathVariable("id") int id) {
		return "/";
	}
	
	/**
	 * Shows user profile
	 * @param login - user
	 * @return user profile page
	 */
	@GetMapping(value = "/profile/{user}")
	public ModelAndView showProfile(@PathVariable("user") String login) {
		BlogUser user = userService.getUser(login);
		ModelAndView model = new ModelAndView("profile", "user", user);
		return model;
	}
	
	/**
	 * Handles search request
	 * @param request - HttpServletRequest object
	 * @return list of results (Post objects)
	 */
	@PostMapping(value = {"/search", "/posts/search"})
	public ModelAndView search(HttpServletRequest request) {
		String pattern = request.getParameter("pattern");
		if (pattern != null && pattern.trim() != "") {
			List<Post> found = postingService.search(pattern);					
			return new ModelAndView("search", "results", found);
		} else {
			return new ModelAndView("search", "noResults", "Sorry, there are no posts matching your request!");
		}		
	}
	
	@GetMapping(value = "/search")
	public String showSearchResults(Model model) {			
		return "search";
	}
}
