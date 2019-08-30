package com.digitalsingular.todo.user;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.digitalsingular.todo.list.TodoList;
import com.google.common.collect.Sets;

@Entity
@Table(name = "USERS")
public class User implements UserDetails {

	enum Role {
		ADMIN, USER;
	}

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String login;

	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@NotBlank
	private String password;

	@ManyToMany(mappedBy = "users",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<TodoList> lists;

	private User() {
		super();
		lists = Sets.newHashSet();
		role = Role.USER;
	}

	public User(String login, String email) {
		this();
		this.login = login;
		this.email = email;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "User [id=" + id + ", login=" + login + ", email=" + email + ", role=" + role + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Sets.newHashSet(new SimpleGrantedAuthority("ROLE_" + role.name().toUpperCase()));
	}

	@Override
	public String getUsername() {
		return getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
