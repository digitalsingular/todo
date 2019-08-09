package com.digitalsingular.todo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.digitalsingular.todo.model.list.TodoList;
import com.digitalsingular.todo.model.list.TodoListService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
@ComponentScan("com.digitalsingular.todo")
public class AppConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		config.setConnectionTestQuery(environment.getProperty("jdbc.testQuery"));
		config.setUsername(environment.getProperty("jdbc.user"));
		config.setPassword(environment.getProperty("jdbc.password"));
		config.setJdbcUrl(environment.getProperty("jdbc.url"));
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public TodoListService todoListService() {
		return new TodoListService(new TodoList());
	}
}
