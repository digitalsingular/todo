package com.digitalsingular.todo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.digitalsingular.todo.model.item.TodoItem;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ctx.getBean("welcomeList");
		TodoItem item = (TodoItem) ctx.getBean("welcomeItem");
		System.out.print(item);
	}
}
