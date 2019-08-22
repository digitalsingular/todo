package com.digitalsingular.todo.item;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "TODO_ITEMS")
public class TodoItem {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	public TodoItem(String description) {
		super();
		if (StringUtils.isEmpty(description)) {
			throw new IllegalArgumentException(
					"No se puede crear un item sin descripcion");
		}
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
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