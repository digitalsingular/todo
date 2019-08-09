package com.digitalsingular.todo.model.list;

import org.springframework.transaction.annotation.Transactional;

import com.digitalsingular.todo.model.item.TodoItem;

@Transactional
public class TodoListService {

	private TodoList initialList;

	public TodoListService(TodoList initialList) {
		super();
		this.initialList = initialList;
	}

	public TodoList createList() {
		return initialList;
	}

	public void createList(TodoItem initialItem) {
		if (initialItem == null) {
			throw new IllegalArgumentException();
		}
	}
}
