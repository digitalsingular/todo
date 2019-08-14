package com.digitalsingular.todo.model.item;

public class TodoItem {

	private String description;

	public TodoItem(String description) throws IllegalArgumentException {
		if (description.isBlank()) {
			throw new IllegalArgumentException("No se puede crear un item sin descripcion");
		}
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
