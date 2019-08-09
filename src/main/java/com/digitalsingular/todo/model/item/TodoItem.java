package com.digitalsingular.todo.model.item;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TodoItem {

	@NotNull(message = "Description can't be null")
	@NotEmpty(message = "Description can't be empty")
	private String description;

	public TodoItem(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "TodoItem [description=" + description + "]";
	}
}
