package com.digitalsingular.todo;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class,
				TestAppConfiguration.class);
		Map<String, String> databaseProperties = (Map<String, String>) ctx.getBean("databaseProperties");
		System.out.println(databaseProperties);
	}
}
