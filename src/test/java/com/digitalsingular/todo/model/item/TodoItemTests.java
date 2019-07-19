package com.digitalsingular.todo.model.item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TodoItemTests {

	@Test
	public void givenADescriptionItemShouldBeBuilt() {
		//Arrange
		String description = "My new item";
		//Act
		TodoItem item = new TodoItem(description);
		//Assert
		assertThat(item.getDescription(), is(description));
	}
}
