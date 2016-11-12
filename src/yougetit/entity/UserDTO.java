/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import yougetit.validator.PasswordsMatch;

/**
 * User data transfer object class. Used during user registration and
 * authentication
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	04.08.2016
 *
 */
@PasswordsMatch
public class UserDTO {
		
    @NotEmpty
    @Size(min=2, max=30)
    private String userName;
     
    @NotEmpty
    @Size(min=2, max=25)
    private String login;
     
    @NotEmpty
    @Size(min=6, max=20)
    private String password;
    
    @NotEmpty
    @Size(min=6, max=20)    
    private String matchingPassword;
     
    @NotEmpty
    @Email
    private String email;

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final String getLogin() {
		return login;
	}

	public final void setLogin(String login) {
		this.login = login;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getMatchingPassword() {
		return matchingPassword;
	}

	public final void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}
}
