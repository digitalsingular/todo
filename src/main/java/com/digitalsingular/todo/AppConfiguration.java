package com.digitalsingular.todo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.google.common.collect.Maps;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

	@Value("${jdbc.driverClassName}")
	private String driver;

	@Autowired
	private Environment environment;

	@Bean
	public Map<String, String> databaseProperties() {
		Map<String, String> databaseProperties = Maps.newHashMap();
		databaseProperties.put("driver", driver);
		databaseProperties.put("url", environment.getProperty("jdbc.url"));
		return databaseProperties;
	}
}
