package com.digitalsingular.todo.list;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.digitalsingular.todo.item.TodoItem;

public class TodoListTest {

	private TodoList sut = null;

	@Before
	public void setUp() {
		sut = new TodoList("sut");
	}

	@Test
	public void givenANewListDescriptionShouldBeTheStated() {
		String description = "sut";
		TodoList sut = new TodoList(description);
		assertThat(sut.getDescription()).isEqualTo(description);
	}

	@Test
	public void givenAnEmptyListShouldContainNoPendingItems() {
		assertThat(sut.getPending()).isEmpty();
		assertThat(sut.getNumberOfPending()).isEqualTo(0);
	}

	@Test
	public void givenAnEmptyListShouldContainNoCompletedItems() {
		assertThat(sut.getCompleted()).isEmpty();
		assertThat(sut.getNumberOfCompleted()).isEqualTo(0);
	}

	@Test
	public void givenAListAddingANewItemShouldStoreItInPending() {
		// Arrange
		TodoItem item = new TodoItem("My new item");
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getPending()).contains(item);
	}

	@Test
	public void givenAListAddingANewItemShouldIncreasePendingItemsNumber() {
		// Arrange
		int startPendingItemsNumber = sut.getNumberOfPending();
		TodoItem item = new TodoItem("My new item");
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getNumberOfPending()).isEqualTo(startPendingItemsNumber + 1);
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
		assertThat(sut.getPending()).isEqualTo(startPending);
		assertThat(sut.getNumberOfPending()).isEqualTo(startPendingItemsNumber);
	}

	@Test
	public void givenAListAddingANewItemShouldNotStoreItInCompleted() {
		// Arrange
		TodoItem item = new TodoItem("My new item");
		List<TodoItem> startCompleted = sut.getCompleted();
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getCompleted()).isEqualTo(startCompleted);
	}

	@Test
	public void givenAListAddingANewItemShouldNotIncreaseCompletedItemsNumber() {
		// Arrange
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		TodoItem item = new TodoItem("My new item");
		// Act
		sut.add(item);
		// Assert
		assertThat(sut.getNumberOfCompleted()).isEqualTo(startCompletedItemsNumber);
	}

	@Test
	public void givenTwoListsWithDifferentDescriptionShouldNotBeEquals() {
		TodoList list1 = new TodoList("Test List 1");
		TodoList list2 = new TodoList("Test List 2");
		assertThat(list1.equals(list2)).isFalse();
	}

	@Test
	public void givenAnEmptyListCompletingAnItemShouldDoNothing() {
		// Arrange
		List<TodoItem> startCompleted = sut.getCompleted();
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		TodoItem item = new TodoItem("My new item");
		// Act
		item.complete();
		// Assert
		assertThat(sut.getCompleted()).isEqualTo(startCompleted);
		assertThat(sut.getNumberOfCompleted()).isEqualTo(startCompletedItemsNumber);
	}

	@Test
	public void givenAListCompletingANonAddedItemShouldDoNothing() {
		// Arrange
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		List<TodoItem> startCompletedItems = sut.getCompleted();
		TodoItem addedItem = new TodoItem("My added item");
		TodoItem nonAddedItem = new TodoItem("My non added item");
		// Act
		sut.add(addedItem);
		nonAddedItem.complete();
		// Assert
		assertThat(sut.getCompleted()).isEqualTo(startCompletedItems);
		assertThat(sut.getNumberOfCompleted()).isEqualTo(startCompletedItemsNumber);
	}

	@Test
	public void givenAListCompletingAnAddedItemShouldMoveIt() {
		// Arrange
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		TodoItem addedItem = new TodoItem("My added item");
		// Act
		sut.add(addedItem);
		int startPendingItemsNumber = sut.getNumberOfPending();
		addedItem.complete();
		// Assert
		assertThat(sut.getNumberOfPending()).isEqualTo(startPendingItemsNumber - 1);
		assertThat(sut.getPending()).doesNotContain(addedItem);
		assertThat(sut.getNumberOfCompleted()).isEqualTo(startCompletedItemsNumber + 1);
		assertThat(sut.getCompleted()).contains(addedItem);
	}
}
