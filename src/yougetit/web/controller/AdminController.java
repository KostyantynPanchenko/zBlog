/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import yougetit.entity.BlogUser;
import yougetit.entity.Comment;
import yougetit.entity.Post;
import yougetit.service.CommentService;
import yougetit.service.PostService;
import yougetit.service.UserService;

/**
 * This class handles requests addressed to administration page.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	29.07.2016
 */
@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postingService;
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping(value = "/admin")
	public ModelAndView adminPageGet() {
		return new ModelAndView("admin");
	}
	
	@PostMapping(value = "/admin")
	public ModelAndView adminPagePost(HttpServletRequest request) {
		switch (request.getParameter("section")) {

		// handling users operations
		case "users":
			return usersTab(request);	

		// handling posts operations
		case "posts":
			return postsTab(request);
			
		// handling comments operations
		case "comments":
			return commentsTab(request);
		}
		return new ModelAndView("admin", "tabName", "Users");
	}	
	
	/**
	 * Processes requests from Users tab in admin console
	 * @param request - HttpServletRequest
	 * @return ModelAndView object according to request
	 */
	private ModelAndView usersTab(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("admin");
		model.addObject("tabName", "Users");
		BlogUser user = null;
		
		switch (request.getParameter("action")) {
		case "findAll":
			List<BlogUser> users = userService.findAll();
			model.addObject("theUsers", users);
			return model;
		case "findById":
			int id = Integer.parseInt(request.getParameter("userId"));
			user = userService.findById(id);
			model.addObject("theUser", user);
			break;
		case "findByName":
			String name = request.getParameter("userName");
			user = userService.getUser(name);
			model.addObject("theUser", user);
			return model;
		}
		return model;
	}
	
	/**
	 * Processes requests from Posts tab in admin console
	 * @param request - HttpServletRequest
	 * @return ModelAndView object according to request
	 */
	private ModelAndView postsTab(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("admin");
		model.addObject("tabName", "Posts");		
		List<Post> posts = null;
		
		switch (request.getParameter("action")) {
		case "findAll":
			posts = postingService.findAll();
			model.addObject("thePosts", posts);
			return model;
		case "findById":
			int id = Integer.parseInt(request.getParameter("postId"));
			Post post = postingService.findById(id);
			model.addObject("thePost", post);
			break;
		case "findByName":
			String name = request.getParameter("postAuthor");
			BlogUser author = userService.getUser(name);
			posts = postingService.getByAuthor(author);
			model.addObject("thePosts", posts);
			return model;
		}
		return model;
	}
	
	/**
	 * Processes requests from Comments tab in admin console
	 * @param request - HttpServletRequest
	 * @return ModelAndView object according to request
	 */
	private ModelAndView commentsTab(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("admin");
		model.addObject("tabName", "Comments");
		List<Comment> comments = null;
		
		switch (request.getParameter("action")) {
		case "findAll":
			comments = commentService.findAll();
			model.addObject("theComments", comments);
			return model;
		case "findById":
			int id = Integer.parseInt(request.getParameter("commentId"));
			Comment comment = commentService.findById(id);
			model.addObject("theComment", comment);
			break;
		case "findByName":
			String name = request.getParameter("commentAuthor");
			BlogUser author = userService.getUser(name);
			comments = commentService.findByAuthor(author);
			model.addObject("theComments", comments);
			return model;
		}
		return model;
	}
	
	/**
	 * Handles request to find all users.
	 * @return logical view name.
	 */
	@GetMapping(value = "/admin/users")
	public String allUSers() {
		System.out.println("Get - displaying all users");
		return "admin";
	}
	
	/**
	 * Handles request to find users.
	 * @param request HttpServletRequest instance.
	 * @return logical view name.
	 */
	@PostMapping(value = "/admin/users")
	public String byId(HttpServletRequest request) {
		System.out.println("USERS");
				
		switch (request.getParameter("action")) {
		case "findAll":
			System.out.println("findAll");
			break;
		case "findById":
			System.out.println("findById");
			break;
		case "findByName":
			System.out.println("findByName");
			break;			
		}
		
		return "admin";
	}
	
	/**
	 * Handles request to show all posts.
	 * @param request HttpServletRequest instance.
	 * @return ModelAndView instance.
	 */
	@PostMapping(value = "/admin/posts")
	public ModelAndView showAllPosts(HttpServletRequest request) {
		System.out.println("POSTS");
		ModelAndView model = new ModelAndView("admin", "tabName", "Posts");
		return model;
	}
	
	/**
	 * Handles request to show all comments.
	 * @param request HttpServletRequest instance.
	 * @return ModelaAndView instance
	 */
	@PostMapping(value = "/admin/comments")
	public ModelAndView showAllComments(HttpServletRequest request) {
		System.out.println("COMMENTS");
		ModelAndView model = new ModelAndView("admin", "tabName", "Comments");
		return model;
	}
}
