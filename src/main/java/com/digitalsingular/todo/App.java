package com.digitalsingular.todo;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.digitalsingular.todo.model.item.TodoItem;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfiguration.class);
		Validator validator = (Validator) ctx.getBean("validator");
		TodoItem nullDescriptionItem = new TodoItem(null);
		Set<ConstraintViolation<TodoItem>> errors =
				validator.validate(nullDescriptionItem);
		for (ConstraintViolation<TodoItem> error: errors) {
			System.out.println(error.getMessage());
		}
	}
}
