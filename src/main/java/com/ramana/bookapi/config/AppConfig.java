package com.ramana.bookapi.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value= {
		@ComponentScan("com.ramana.bookapi.dao"),
		@ComponentScan("com.ramana.bookapi.service"),
})
public class AppConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		
		props.put(DRIVER, env.getProperty("mysql.driver"));
		props.put(URL, env.getProperty("mysql.url"));
		props.put(USER, env.getProperty("mysql.user"));
		props.put(PASS, env.getProperty("mysql.password"));
		
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENT, env.getProperty("hibernate.c3p0.max_statements"));
		
		
		return localSessionFactoryBean;
	}
}
