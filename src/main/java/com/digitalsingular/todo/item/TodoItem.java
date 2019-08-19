package com.digitalsingular.todo.item;

import io.micrometer.core.instrument.util.StringUtils;

public class TodoItem {

	private String description;

	public TodoItem(String description) throws IllegalArgumentException {
		if (StringUtils.isBlank(description)) {
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
