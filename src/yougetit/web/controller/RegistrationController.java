/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yougetit.entity.BlogUser;
import yougetit.entity.UserDTO;
import yougetit.exceptions.EmailExistException;
import yougetit.exceptions.UserExistsException;
import yougetit.service.UserService;

/**
 * Controller which handles registration requests.
 * 
 * @author  Kostyantyn Panchenko
 * @version 1.0
 * @since   29.07.2016
 */
@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	/**
	 * Processing access to registration page 
	 */
	@GetMapping(value = "/register") 
	public ModelAndView showRegisterForm() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new UserDTO());
		return model;
	}	
	
	/**
	 * Processing registration form submission
	 * @param userDTO - user transfer object binded to registration form
	 * @param errors - binding and validation errors 
	 */
	@PostMapping(value = "/register")
	public String processRegistration(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult errors, RedirectAttributes model) {
		if (errors.hasErrors()) {
			return "/register";
		}
		
		BlogUser newUser = null;
		
		try {
			newUser = createNewBlogUserAccount(userDTO);
		} catch (UserExistsException e) {			
			errors.rejectValue("userName", "error.userName.exist");
			return "/register";
		} catch (EmailExistException e) {			
			errors.rejectValue("email", "error.email.exist");
			return "/register";
		}
		
		userService.save(newUser);
		model.addFlashAttribute("username", newUser.getUsername());
		
		return "redirect:/login";
	}

	/**
	 * Creates new blog user
	 * @param userDTO - user data transfer object received from form input
	 * @return new BlogUser
	 * @throws UserExistsException
	 * @throws EmailExistException
	 */
	private BlogUser createNewBlogUserAccount(UserDTO userDTO) throws UserExistsException, EmailExistException{
		BlogUser newUser = userService.getUser(userDTO.getLogin());
		
		if (newUser != null) {
			throw new UserExistsException();
		}  else {
			newUser = new BlogUser();
		}
		
		if (newUser.getEmail() == userDTO.getEmail()) {
			throw new EmailExistException();
		}
		
		newUser.setUsername(userDTO.getLogin());
		newUser.setEmail(userDTO.getEmail());
		newUser.setPassword(getEncodedPassword(userDTO.getPassword()));
		return newUser;
	}
	
	/**
	 * SHA1 password encoder
	 * @param original - password as entered by user
	 * @return encoded in SHA1 password
	 */
	private String getEncodedPassword(String original) {
			
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] result = md.digest(original.getBytes());
	        StringBuilder sb = new StringBuilder();
	        
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return original;
		}
	}
}
