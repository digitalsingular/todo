package com.digitalsingular.todo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.digitalsingular.todo.model.list.TodoListService;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
		TodoListService service = ctx.getBean(TodoListService.class);
		service.createList();
		service.addItem(null);
	}
}
