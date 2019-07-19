package com.digitalsingular.todo.model.list;

import java.util.ArrayList;
import java.util.List;

import com.digitalsingular.todo.model.item.TodoItem;
import com.google.common.collect.ImmutableList;

public class TodoList {

	private List<TodoItem> pending = new ArrayList<>();
	
	public int getNumberOfPending() {
		return pending.size();
	}

	public void add(TodoItem item) {
		if(item != null) {
			this.pending.add(item);
		}
	}

	public List<TodoItem> getPending() {
		return ImmutableList.copyOf(pending);
	}

	public int getNumberOfCompleted() {
		return 0;
	}

}
