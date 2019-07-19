package com.digitalsingular.todo.model.list;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TodoListTests {

	@Test
	public void givenANewListShouldContainNoItems() {
		//Act
		TodoList list = new TodoList();
		//Assert
		assertThat(list.size(), is(0));
	}
}
