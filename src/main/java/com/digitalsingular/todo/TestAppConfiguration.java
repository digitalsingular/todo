package com.digitalsingular.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.digitalsingular.todo.model.item.TodoItem;

@Configuration
@Profile("test")
public class TestAppConfiguration {

	@Bean
	public TodoItem todoItem() {
		return new TodoItem("Test item");
	}
}
