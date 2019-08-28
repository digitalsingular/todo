package com.digitalsingular.todo.list;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

@Service
@Transactional(readOnly = true)
public class TodoListService {

	@Autowired
	private TodoListRepository repository;

	public Set<TodoList> getAll() {
		return Sets.newHashSet(repository.findAll());
	}

	public Optional<TodoList> get(long id) {
		return repository.findById(id);
	}

	@Transactional(readOnly = false)
	public TodoList add(TodoList todoList) {
		return repository.save(todoList);
	}

	@Transactional(readOnly = false)
	public TodoList save(TodoList todoList) {
		return repository.save(todoList);
	}

}
