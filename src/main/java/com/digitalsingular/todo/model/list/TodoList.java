package com.digitalsingular.todo.model.list;

import java.util.List;

import com.digitalsingular.todo.model.item.TodoItem;
import com.google.common.collect.ImmutableList;

public class TodoList {

	private TodoItem pending;
	
	public int getNumberOfPending() {
		return 0;
	}

	public void add(TodoItem item) {
		this.pending = item;
	}

	public List<TodoItem> getPending() {
		return ImmutableList.of(pending);
	}

	public int getNumberOfCompleted() {
		return 0;
	}

}
