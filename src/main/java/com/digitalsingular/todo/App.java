package com.digitalsingular.todo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.digitalsingular.todo.model.list.TodoList;
import com.digitalsingular.todo.model.list.TodoListService;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		TodoListService listService = (TodoListService) ctx.getBean("todoListService");
		TodoList list = listService.createList();
		System.out.println(list);
	}
}
