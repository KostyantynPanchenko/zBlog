package yougetit.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * WebAppSecurityConfig will be picked up automatically because of ComponentScan
 * annotation is present. Otherwise in case of absence of current class root
 * configuration should be made by simply calling super(WebSecurityConfig.class)
 * in constructor of WebAppSecurityInitializer class. Or WebSecurityConfig can
 * be imported in WebAppConfig class.
 * 
 * @author Kostyantyn Panchenko
 * @version 1.0
 * @since 29.07.2016
 */
@Configuration
@ComponentScan
public class RootConfig {
	
}
