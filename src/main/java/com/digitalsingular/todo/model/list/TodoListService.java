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

	public TodoList getList() {
		return initialList;
	}

	public void addItem(TodoItem item) {
		if (item == null) {
			throw new IllegalArgumentException();
		} else {
			initialList.add(item);
		}
	}
}
