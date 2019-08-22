package com.digitalsingular.todo.list;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.digitalsingular.todo.item.TodoItem;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Entity
@Table(name = "TODO_LISTS")
public class TodoList {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	@Transient
	private List<TodoItem> pending;

	@Transient
	private List<TodoItem> completed;

	public TodoList(String description) {
		super();
		this.description = description;
		pending = Lists.newArrayList();
		completed = Lists.newArrayList();
	}

	public Long getId() {
		return id;
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

	@Override
	public int hashCode() {
		return Objects.hash(completed, description, pending);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TodoList other = (TodoList) obj;
		return Objects.equals(completed, other.completed) && Objects.equals(description, other.description)
				&& Objects.equals(pending, other.pending);
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
