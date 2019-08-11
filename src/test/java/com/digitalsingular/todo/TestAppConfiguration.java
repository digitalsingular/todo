package com.digitalsingular.todo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("test")
public class TestAppConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		System.out.println("Instanciando datasource de pruebas");
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		config.setConnectionTestQuery(environment.getProperty("jdbc.testQuery"));
		config.setUsername(environment.getProperty("jdbc.user"));
		config.setPassword(environment.getProperty("jdbc.password"));
		config.setJdbcUrl(environment.getProperty("jdbc.url"));
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}
}
