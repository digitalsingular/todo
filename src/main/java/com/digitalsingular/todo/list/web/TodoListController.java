package com.digitalsingular.todo.list.web;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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
	public Set<TodoList> getLists() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public TodoList getList(@PathVariable long id) {
		return service.get(id).orElseThrow(
				() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "No existe una lista con id " + id));
	}

	@PostMapping
	public ResponseEntity<TodoList> addList(@RequestBody TodoList todoList,
			UriComponentsBuilder ucBuilder) {
		TodoList newList = service.add(todoList);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("lists/{id}").buildAndExpand(newList.getId()).toUri());
		return new ResponseEntity<>(newList, headers, HttpStatus.CREATED);
	}

	@PutMapping
	public TodoList updateList(@RequestBody TodoList todoList) {
		TodoList outdatedTodoList = service.get(todoList.getId())
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "No existe una lista con id " + todoList.getId()));
		outdatedTodoList.setUsers(todoList.getUsers());
		outdatedTodoList = service.save(outdatedTodoList);
		return outdatedTodoList;
	}
}
