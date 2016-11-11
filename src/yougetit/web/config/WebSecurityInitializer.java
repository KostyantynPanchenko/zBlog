package yougetit.web.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * By extending AbstractSecurityWebApplicationInitializer (which implements 
 * WebApplicationInitializer) we enable this class to be discovered by Spring 
 * and to be used to register DelegatingFilterProxy with the web container.
 * 
 * This class also register the springSecurityFilterChain bean (FilterChainProxy) 
 * for every URL in theBlog application. No additional customization is needed.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	29.07.2016
 */
public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
