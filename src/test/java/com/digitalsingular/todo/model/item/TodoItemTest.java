package com.digitalsingular.todo.model.item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TodoItemTest {

	@Test
	public void cobertura50Porciento() {
		TodoItem item =  new TodoItem("tadá!");
	}
	
	@Test
	public void cobertura100Porcierto() {
		TodoItem item =  new TodoItem("tadá!");
		item.getDescription();
	}
}
