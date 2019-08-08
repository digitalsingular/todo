package com.digitalsingular.todo.model.list;

public class TodoListService {

	private TodoList initialList;

	public TodoListService(TodoList initialList) {
		super();
		this.initialList = initialList;
	}

	public TodoList createList() {
		return initialList;
	}
}
