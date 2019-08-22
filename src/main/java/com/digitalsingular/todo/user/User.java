package com.digitalsingular.todo.user;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Set<TodoList> lists;

	public User(String login, String email) {
		super();
		this.login = login;
		this.email = email;
		lists = Sets.newHashSet();
	}

	public void addList(String description) {
		lists.add(new TodoList(description));
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

	public Set<TodoList> getLists() {
		return lists;
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
