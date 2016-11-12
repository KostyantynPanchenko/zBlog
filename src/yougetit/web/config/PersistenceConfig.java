/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.web.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Persistence configuration class.
 * @author Kostyantyn Panchenko
 * @version 1.2
 * @since   09.08.2016
 */
@Configuration
@EnableTransactionManagement
@PropertySources({
	@PropertySource("config/jdbc.properties"),
	@PropertySource("config/hibernate.properties")})
@ComponentScan
public class PersistenceConfig {
	
    /**
     * Instance of environment in which the current application is running.
     */
	@Autowired
	private Environment env;	
	
	/**
	 * Creates LocalSessionFactoryBean.
	 * @param dataSource DataSource instance.
	 * @return LocalSessionFactory bean.
	 */
	@Autowired
	@Bean(name = "sessionFactory")
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] {"yougetit.entity"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}

	/**
	 * Creates DataSource bean.
	 * @return DataSource bean.
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		ds.setUrl(env.getProperty("jdbc.url"));
		ds.setUsername(env.getProperty("jdbc.username"));
		ds.setPassword(env.getProperty("jdbc.password"));
		return ds;
	}

	/**
	 * Creates HibernateTransactionManager bean. Binds a Hibernate Session from the specified 
	 * factory to the thread, potentially allowing for one thread-bound Session per factory.
	 * @param sessionFactory SessionFactory instance.
	 * @return HibernateTransactionManager bean.
	 */
	@Autowired
	@Bean	
	public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

	/**
	 * Creates PersistenceExceptionTranslationPostProcessor bean.
	 * @return PersistenceExceptionTranslationPostProcessor bean.
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/**
	 * @return Properties instance populated with Hibernate properties.
	 */
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		return prop;
	}
}
