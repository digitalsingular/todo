package com.digitalsingular.todo.model.list;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import static org.hamcrest.Matchers.empty;

import org.junit.Before;
import org.junit.Test;

import com.digitalsingular.todo.model.item.TodoItem;

public class TodoListTests {
	
	private TodoList sut = null;
	
	@Before
	public void setUp() {
		sut = new TodoList();
	}

	@Test
	public void givenAnEmptyListShouldContainNoPendingItems() {
		assertThat(sut.getPending(), is(empty()));
		assertThat(sut.getNumberOfPending(), is(0));
	}

	@Test
	public void givenAnEmptyListShouldContainNoCompletedItems() {
		assertThat(sut.getCompleted(), is(empty()));
		assertThat(sut.getNumberOfCompleted(), is(0));
	}

	@Test
	public void givenAListAddingANewItemShouldStoreItInPending() {
		// Arrange
		TodoItem item = new TodoItem("My new item");
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getPending(), contains(item));
	}

	@Test
	public void givenAListAddingANewItemShouldIncreasePendingItemsNumber() {
		// Arrange
		int startPendingItemsNumber = sut.getNumberOfPending();
		TodoItem item = new TodoItem("My new item");
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getNumberOfPending(), is(startPendingItemsNumber + 1));
	}
	
	@Test
	public void givenAListAddingANullItemShouldDoNothing() {
		// Arrange
		TodoItem item = null;
		List<TodoItem> startPending = sut.getPending();
		int startPendingItemsNumber = sut.getNumberOfPending();
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getPending(), is(startPending));
		assertThat(sut.getNumberOfPending(), is(startPendingItemsNumber));
	}

	@Test
	public void givenAListAddingANewItemShouldNotStoreItInCompleted() {
		// Arrange
		TodoItem item = new TodoItem("My new item");
		List<TodoItem> startCompleted = sut.getCompleted();
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getCompleted(), is(startCompleted));
	}
	
	@Test
	public void givenAListAddingANewItemShouldNotIncreaseCompletedItemsNumber() {
		// Arrange
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		TodoItem item = new TodoItem("My new item");
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getNumberOfCompleted(), is(startCompletedItemsNumber));
	}

	@Test
	public void givenAnEmptyListCompletingAnItemShouldDoNothing() {
		// Arrange
		List<TodoItem> startCompleted = sut.getCompleted();
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		TodoItem item = new TodoItem("My new item");
		// Act
		sut.complete(item);
		// Assert
		assertThat(sut.getCompleted(), is(startCompleted));
		assertThat(sut.getNumberOfCompleted(), is(startCompletedItemsNumber));
	}

	@Test
	public void givenAListCompletingANullItemShouldDoNothing() {
		// Arrange
		List<TodoItem> startCompleted = sut.getCompleted();
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		// Act
		sut.complete(null);
		// Assert
		assertThat(sut.getCompleted(), is(startCompleted));
		assertThat(sut.getNumberOfCompleted(), is(startCompletedItemsNumber));
	}
	
	@Test
	public void givenAListCompletingANonAddedItemShouldDoNothing() {
		//Arrange
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		List<TodoItem> startCompletedItems = sut.getCompleted();
		TodoItem addedItem = new TodoItem("My added item");
		TodoItem nonAddedItem = new TodoItem("My non added item");
		//Act
		sut.add(addedItem);
		sut.complete(nonAddedItem);
		//Assert
		assertThat(sut.getCompleted(), is(startCompletedItems));
		assertThat(sut.getNumberOfCompleted(), is(startCompletedItemsNumber));
	}
	
	@Test
	public void givenAListCompletingAnAddedItemShouldMoveIt() {
		//Arrange
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		TodoItem addedItem = new TodoItem("My added item");
		//Act
		sut.add(addedItem);
		int startPendingItemsNumber = sut.getNumberOfPending();
		sut.complete(addedItem);
		//Assert
		assertThat(sut.getNumberOfPending(), is(startPendingItemsNumber-1));
		assertThat(sut.getPending(), not(contains(addedItem)));
		assertThat(sut.getNumberOfCompleted(), is(startCompletedItemsNumber+1));
		assertThat(sut.getCompleted(), contains(addedItem));
	}
}
