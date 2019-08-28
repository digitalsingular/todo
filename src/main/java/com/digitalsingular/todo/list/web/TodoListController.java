package com.digitalsingular.todo.list.web;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.digitalsingular.todo.list.TodoList;
import com.digitalsingular.todo.list.TodoListService;

@RestController
@RequestMapping("lists")
public class TodoListController {

	private final TodoListService service;

	public TodoListController(TodoListService service) {
		super();
		this.service = service;
	}

	@GetMapping("")
	public Set<TodoList> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public TodoList getById(@PathVariable long id) {
		return service.get(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe una lista con id " + id));
	}
}
