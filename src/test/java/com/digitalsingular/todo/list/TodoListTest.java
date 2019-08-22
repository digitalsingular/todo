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
	public void givenTwoListsWithTheSameDescriptionAndTheSamePendingItemsShouldBeEquals() {
		String listDescription = "Test List";
		TodoItem item1 = new TodoItem("Test Item 1");
		TodoItem item2 = new TodoItem("Test Item 2");
		TodoList list1 = new TodoList(listDescription);
		TodoList list2 = new TodoList(listDescription);
		list1.add(item1);
		list1.add(item2);
		list2.add(item1);
		list2.add(item2);
		assertThat(list1.equals(list2)).isTrue();
	}

	@Test
	public void givenTwoListsWithTheSameDescriptionAndDifferentPendingItemsShouldBeNotEquals() {
		String listDescription = "Test List";
		TodoItem item1 = new TodoItem("Test Item 1");
		TodoItem item2 = new TodoItem("Test Item 2");
		TodoList list1 = new TodoList(listDescription);
		TodoList list2 = new TodoList(listDescription);
		list1.add(item1);
		list1.add(item2);
		list2.add(item1);
		assertThat(list1.equals(list2)).isFalse();
	}

	@Test
	public void givenTwoListsWithTheSameDescriptionTheSamePendingItemsAndTheSameCompletedItemsShouldBeEquals() {
		String listDescription = "Test List";
		TodoItem item1 = new TodoItem("Test Item 1");
		TodoItem item2 = new TodoItem("Test Item 2");
		TodoList list1 = new TodoList(listDescription);
		TodoList list2 = new TodoList(listDescription);
		list1.add(item1);
		list1.add(item2);
		list2.add(item1);
		list2.add(item2);
		list1.complete(item1);
		list2.complete(item1);
		assertThat(list1.equals(list2)).isTrue();
	}

	@Test
	public void givenTwoListsWithTheSameDescriptionTheSamePendingItemsAndDifferentCompletedItemsShouldNotBeEquals() {
		String listDescription = "Test List";
		TodoItem item1 = new TodoItem("Test Item 1");
		TodoItem item2 = new TodoItem("Test Item 2");
		TodoList list1 = new TodoList(listDescription);
		TodoList list2 = new TodoList(listDescription);
		list1.add(item1);
		list1.add(item2);
		list2.add(item1);
		list2.add(item2);
		list1.complete(item1);
		list2.complete(item2);
		assertThat(list1.equals(list2)).isFalse();
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
		assertThat(sut.getCompleted()).isEqualTo(startCompleted);
		assertThat(sut.getNumberOfCompleted()).isEqualTo(startCompletedItemsNumber);
	}

	@Test
	public void givenAListCompletingANullItemShouldDoNothing() {
		// Arrange
		List<TodoItem> startCompleted = sut.getCompleted();
		int startCompletedItemsNumber = sut.getNumberOfCompleted();
		// Act
		sut.complete(null);
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
		sut.complete(nonAddedItem);
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
		sut.complete(addedItem);
		// Assert
		assertThat(sut.getNumberOfPending()).isEqualTo(startPendingItemsNumber - 1);
		assertThat(sut.getPending()).doesNotContain(addedItem);
		assertThat(sut.getNumberOfCompleted()).isEqualTo(startCompletedItemsNumber + 1);
		assertThat(sut.getCompleted()).contains(addedItem);
	}
}
