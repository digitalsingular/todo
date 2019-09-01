package com.digitalsingular.todo.item;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.digitalsingular.todo.list.TodoList;

@Entity
@Table(name = "TODO_ITEMS")
public class TodoItem {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	@Enumerated(EnumType.STRING)
	private ItemStatus status;

	@ManyToOne
	@JoinColumn(name = "list_id")
	private TodoList list;

	private TodoItem() {
		super();
	}

	public TodoItem(String description) {
		this();
		if (StringUtils.isEmpty(description)) {
			throw new IllegalArgumentException("No se puede crear un item sin descripcion");
		}
		this.description = description;
		status = ItemStatus.PENDING;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public TodoList getList() {
		return list;
	}

	public void setList(TodoList list) {
		this.list = list;
	}

	public void complete() {
		status = ItemStatus.COMPLETE;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
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
		TodoItem other = (TodoItem) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", description=" + description + "]";
	}
}