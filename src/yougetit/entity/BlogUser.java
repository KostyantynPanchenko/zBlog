/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity class, represents blog user.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	01.08.2016
 */
@Entity
@Table(name = "blog_users")
public class BlogUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	
	@NotNull
	@NotEmpty
	@Column(name = "password", nullable = false, length = 60)	
	private String password;
	
	@NotNull
	@NotEmpty
	@Column(name = "email", nullable = false, length = 60)
	private String email;
	
	@NotNull
	@Column(name = "role", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)	
	private BlogUserRole role;
	
	@NotNull
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	@Column(name = "posts")
	private List<Post> posts = new ArrayList<>();
	
	
	/**
	 * No-args constructor
	 */
	public BlogUser() {
		enabled = true;
		role = BlogUserRole.ROLE_USER;
	}
	
	/**
	 * Field-based constructor
	 * @param login - user's login
	 * @param password - user's password
	 */
	public BlogUser(String login, String password, String email) {
		this();
		this.username = login;
		this.password = password;	
		this.email = email;
	}
	
	/**
	 * Adding new post to usr's post collection
	 * @param post - post to be added
	 * @return true if successfully, false otherwise 
	 */
	public boolean addPost(Post post) {
		boolean result = posts.add(post);
		
		if (post.getAuthor() != this) {
			post.setAuthor(this);
		}
		return result;
	}

	/*
	 * setters and getters
	 */
	public final int getId() {
		return id;
	}
	
	public final String getUsername() {
		return username;
	}

	public final void setUsername(String login) {
		this.username = login;
	}

	public final String getPassword() {
		return password;
	}
	
	public final String getEmail() {
		return email;
	}
	
	public final void setEmail(String email) {
		this.email = email;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final boolean isEnabled() {
		return enabled;
	}

	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public final BlogUserRole getRole() {
		return role;
	}

	public final void setRole(BlogUserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "BlogUser [login=" + username + ", password=" + password + ", enabled=" + enabled + ", role=" + role
				+ "]";
	}	
}
