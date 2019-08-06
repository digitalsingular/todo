package com.digitalsingular.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.digitalsingular.todo.model.list.TodoList;

@Configuration
public class AppConfiguration {

	@Bean
	public TodoList todoList() {
		return new TodoList();
	}
}
