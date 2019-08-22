package com.digitalsingular.todo.user;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.digitalsingular.todo.list.TodoList;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String login;

	private String email;

	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private TodoList list;

	public User(String login, String email) {
		super();
		this.login = login;
		this.email = email;
	}

	public void setNewList(String description) {
		list = new TodoList(this, description);
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

	public TodoList getList() {
		return list;
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
