/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.web.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializes theBlog application by making Spring container aware of all the
 * configurations we've made and exposing DispatchingServlet as the default 
 * application servlet (front controller).
 * 
 * It also ensure that WebSecurityConfig was loaded in application initializer.
 * 
 * In order to initialize before other initializer instances Order annotation is
 * added.
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	29.07.2016
 */
@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * {@inheritDoc}}
     */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { 
			RootConfig.class, 
			WebSecurityConfig.class, 
			PersistenceConfig.class };
	}

	/**
     * {@inheritDoc}}
     */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebAppConfig.class };
	}

	/**
     * {@inheritDoc}}
     */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
