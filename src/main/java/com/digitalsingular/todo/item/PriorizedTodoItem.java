package com.digitalsingular.todo.item;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PRIORIZED_TODO_ITEMS")
public class PriorizedTodoItem extends TodoItem {

	private ItemPriority priority;

	public PriorizedTodoItem(String description,
			ItemPriority priority) {
		super(description);
		this.priority = priority;
	}

	public ItemPriority getPriority() {
		return priority;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


}

enum ItemPriority {
	HIGH, NORMAL, LOW;
}
