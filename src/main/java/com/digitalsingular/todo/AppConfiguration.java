package com.digitalsingular.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.digitalsingular.todo.model.item.TodoItem;
import com.digitalsingular.todo.model.list.TodoList;
import com.digitalsingular.todo.model.list.TodoListService;

@Configuration
public class AppConfiguration {

	@Bean
	public TodoItem todoItem() {
		return new TodoItem("");
	}

	@Bean
	public TodoList todoList(TodoItem item) {
		TodoList list = new TodoList();
		list.add(item);
		return list;
	}

	@Bean
	public TodoListService todoListService(TodoList list) {
		return new TodoListService(list);
	}
}
