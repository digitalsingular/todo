package com.digitalsingular.todo.list;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class TodoListService {

	public Set<TodoList> getAll() {
		return Sets.newHashSet();
	}

}
