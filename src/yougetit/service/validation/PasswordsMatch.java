package yougetit.service.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Type level annotation for validating passwords match
 * 
 * @author Kostyantyn Panchenko
 * @version 1.1
 * @since 09.08.2016
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatchValidator.class)
public @interface PasswordsMatch {
	String message() default "Passwords don\'t match!";
	Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
