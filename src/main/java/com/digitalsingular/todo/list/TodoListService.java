package com.digitalsingular.todo.list;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class TodoListService {

	@Autowired
	private TodoListRepository repository;

	public Set<TodoList> getAll() {
		return Sets.newHashSet(repository.findAll());
	}

	public Optional<TodoList> get(long id) {
		return repository.findById(id);
	}

}
