package com.digitalsingular.todo.model.list;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.digitalsingular.todo.model.item.TodoItem;

public class TodoListTests {

	@Test
	public void givenANewListShouldContainNoPendingItems() {
		//Act
		TodoList list = new TodoList();
		//Assert
		assertThat(list.getNumberOfPending(), is(0));
	}
	
	@Test
	public void givenANewListShouldContainNoCompletedItems() {
		//Act
		TodoList list = new TodoList();
		//Assert
		assertThat(list.getNumberOfCompleted(), is(0));
	}
	
	@Test
	public void givenAListAddingANewItemShouldStoreItInPending() {
		//Arrange
		TodoList list = new TodoList();
		TodoItem item = new TodoItem("My new item");
		//Act
		list.add(item);
		//Assert
		assertThat(list.getPending(), hasItem(item));
	}
}
