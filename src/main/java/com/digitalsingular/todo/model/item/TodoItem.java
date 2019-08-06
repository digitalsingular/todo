package com.digitalsingular.todo.model.item;

public class TodoItem {

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
