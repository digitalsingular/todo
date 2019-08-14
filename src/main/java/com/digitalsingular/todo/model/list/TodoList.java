package com.digitalsingular.todo.model.list;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

	public TodoItem getPendingItem(int position) throws NoSuchElementException {
		TodoItem item = null;
		if (positionInBounds(position, pending)) {
			item = pending.get(0);
		} else {
			throw new NoSuchElementException(
					"No hay elemento en la posicion " + position);
		}
		return item;
	}

	private boolean positionInBounds(int position, List<TodoItem> list) {
		return position > 0 && position < list.size();
	}
}
