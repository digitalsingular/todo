package com.digitalsingular.todo.model.item;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TodoItem {

	@NotNull(message = "Description can't be null")
	@NotEmpty(message = "Description can't be empty")
	private String description;

	public TodoItem(String description) {
		if (description == null || "".equals(description.trim())) {
			throw new IllegalArgumentException("La descripcion del item no puede ser nula o blanca");
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
