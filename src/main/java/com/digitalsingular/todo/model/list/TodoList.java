package com.digitalsingular.todo.model.list;

import java.util.ArrayList;
import java.util.List;

import com.digitalsingular.todo.model.item.TodoItem;
import com.google.common.collect.ImmutableList;

public class TodoList {

	private List<TodoItem> pending = new ArrayList<>();
	private List<TodoItem> completed = new ArrayList<>();

	public int getNumberOfPending() {
		return pending.size();
	}

	public void add(TodoItem item) {
		if (item != null) {
			pending.add(item);
		}
	}

	public List<TodoItem> getPending() {
		return ImmutableList.copyOf(pending);
	}

	public int getNumberOfCompleted() {
		return completed.size();
	}

	public void complete(TodoItem item) {
		if (item != null && !pending.isEmpty() && pending.contains(item)) {
			pending.remove(item);
			completed.add(item);
		}
	}

	public List<TodoItem> getCompleted() {
		return ImmutableList.copyOf(completed);
	}

	@Override
	public String toString() {
		return "TodoList [pending=" + pending + ", completed=" + completed + "]";
	}
}
