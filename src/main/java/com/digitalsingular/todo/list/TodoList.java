package com.digitalsingular.todo.list;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.digitalsingular.todo.item.ItemStatus;
import com.digitalsingular.todo.item.TodoItem;
import com.digitalsingular.todo.user.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
@Table(name = "TODO_LISTS")
public class TodoList {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	@ManyToMany
	@JoinTable(
			name = "USERS_TODO_LISTS",
			joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> users;

	@Transient
	private List<TodoItem> items;

	public TodoList(String description) {
		super();
		this.description = description;
		items = Lists.newArrayList();
		users = Sets.newHashSet();
	}

	public void addUser(User user) {
		if (user != null) {
			users.add(user);
		}
	}

	public void removeUser(User user) {
		users.remove(user);
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void add(TodoItem item) {
		if (item != null) {
			items.add(item);
			item.setList(this);
		}
	}

	private List<TodoItem> getItemsFilteredByStatus(ItemStatus status) {
		return items.stream().filter(item -> item.getStatus() == status).collect(Collectors.toList());
	}

	public int getNumberOfPending() {
		return getPending().size();
	}

	public List<TodoItem> getPending() {
		return getItemsFilteredByStatus(ItemStatus.PENDING);
	}

	public List<TodoItem> getCompleted() {
		return getItemsFilteredByStatus(ItemStatus.COMPLETE);
	}

	public int getNumberOfCompleted() {
		return getCompleted().size();
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
		TodoList other = (TodoList) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TodoList [id=" + id + ", description=" + description + ", items=" + items + "]";
	}
}
