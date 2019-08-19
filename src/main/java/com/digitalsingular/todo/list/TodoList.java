package com.digitalsingular.todo.list;

import java.util.List;

import com.digitalsingular.todo.item.TodoItem;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class TodoList {

	private String description;

	private List<TodoItem> pending;

	private List<TodoItem> completed;

	public TodoList(String description) {
		super();
		this.description = description;
		pending = Lists.newArrayList();
		completed = Lists.newArrayList();
	}

	public String getDescription() {
		return description;
	}

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
