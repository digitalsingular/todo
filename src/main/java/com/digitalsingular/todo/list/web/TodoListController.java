package com.digitalsingular.todo.list.web;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalsingular.todo.list.TodoList;
import com.digitalsingular.todo.list.TodoListService;

@RestController("/lists")
public class TodoListController {

	private final TodoListService service;

	public TodoListController(TodoListService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public Set<TodoList> getAllLists() {
		return service.getAll();
	}
}
