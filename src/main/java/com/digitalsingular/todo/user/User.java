package com.digitalsingular.todo.user;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.digitalsingular.todo.list.TodoList;
import com.google.common.collect.Sets;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String login;

	private String email;

	@ManyToMany(mappedBy = "users",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<TodoList> lists;

	public User(String login, String email) {
		super();
		this.login = login;
		this.email = email;
		lists = Sets.newHashSet();
	}

	public void addList(TodoList list) {
		if (list != null) {
			lists.add(list);
		}
	}

	public void removeList(TodoList list) {
		if (lists.contains(list)) {
			lists.remove(list);
			list.removeUser(this);
		}
	}

	public void setLists(Set<TodoList> lists) {
		this.lists = lists;
	}

	public Set<TodoList> getLists() {
		return lists;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login);
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
		User other = (User) obj;
		return Objects.equals(login, other.login);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", email=" + email + "]";
	}
}
