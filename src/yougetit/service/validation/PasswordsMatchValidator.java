package yougetit.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import yougetit.data.UserDTO;
/**
 * Passwords validator class. Takes UserDTO instance and verify if passwords
 * entered during registration process match.
 * 
 * @author 	Kostyantyn PAnchenko
 * @version 1.0
 * @since	05.08.2016
 */
public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserDTO> {

	@Override
	public void initialize(PasswordsMatch pass) { }

	@Override
	public boolean isValid(UserDTO user, ConstraintValidatorContext arg1) {
		return user.getPassword().equals(user.getMatchingPassword());
	}

}
