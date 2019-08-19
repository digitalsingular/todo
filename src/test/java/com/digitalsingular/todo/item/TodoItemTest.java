package com.digitalsingular.todo.item;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.digitalsingular.todo.item.TodoItem;

public class TodoItemTest {

	@Test(expected = IllegalArgumentException.class)
	public void givenANullDescriptionShouldNotBuildItem() {
		new TodoItem(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenAnEmptyDescriptionShouldNotBuildItem() {
		new TodoItem("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenAnAllBlankDescriptionShouldNotBuildItem() {
		new TodoItem(" ");
	}

	@Test
	public void givenATextDescriptionShouldBuildItem() {
		String description = "test item";
		TodoItem item = new TodoItem(description);
		assertThat(item.getDescription()).isEqualTo(description);
	}
}