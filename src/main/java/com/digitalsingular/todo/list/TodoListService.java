package com.digitalsingular.todo.list;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.digitalsingular.todo.user.User;
import com.digitalsingular.todo.user.UserService;
import com.google.common.collect.Sets;

@Service
@Transactional(readOnly = true)
@Validated
public class TodoListService {

	private UserService userService;

	private TodoListRepository repository;

	public TodoListService(UserService userService, TodoListRepository repository) {
		super();
		this.userService = userService;
		this.repository = repository;
	}

	public Set<TodoList> getAll() {
		return Sets.newHashSet(repository.findAll());
	}

	public Optional<TodoList> get(@Min(1) long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = false)
	public TodoList add(@Valid TodoList todoList) {
		for (User user : todoList.getUsers()) {
			User dbUser = userService.get(user.getId())
					.orElseThrow(() -> new EntityNotFoundException("User with id " + user.getId() + " was not found"));
			todoList.addUser(dbUser);
		}
		return repository.save(todoList);
	}

	@Transactional(readOnly = false)
	public TodoList update(@Valid TodoList todoList) {
		TodoList outdatedTodoList = repository.findById(todoList.getId()).orElseThrow(
				() -> new EntityNotFoundException("TodoList with id " + todoList.getId() + " was not found"));
		for (User user : todoList.getUsers()) {
			User dbUser = userService.get(user.getId())
					.orElseThrow(() -> new EntityNotFoundException("User with id " + user.getId() + " was not found"));
			outdatedTodoList.addUser(dbUser);
		}
		return repository.save(outdatedTodoList);
	}

	public Set<TodoList> getByUserId(@Min(1) long userId) {
		return Sets.newHashSet(repository.findByUsersId(userId));
	}

}
