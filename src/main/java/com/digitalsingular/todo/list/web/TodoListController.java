package com.digitalsingular.todo.list.web;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.digitalsingular.todo.list.TodoList;
import com.digitalsingular.todo.list.TodoListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
public class TodoListController {

	private final TodoListService service;

	public TodoListController(TodoListService service) {
		super();
		this.service = service;
	}

	@Operation(description = "get lists")
	@SecurityRequirement(name = "bearerScheme")
	@GetMapping("/lists")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Set<TodoList> getLists() {
		return service.getAll().stream().map(list -> {
			list.getUsers().clear();
			return list;
		}).collect(Collectors.toSet());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/lists/{id}")
	public TodoList getList(@Min(1) @PathVariable long id) {
		TodoList list = service.get(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe una lista con id " + id));
		list.getUsers().clear();
		return list;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/lists")
	public ResponseEntity<TodoList> addList(@RequestBody @Valid TodoList todoList, UriComponentsBuilder ucBuilder) {
		TodoList newList = service.add(todoList);
		newList.getUsers().clear();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("lists/{id}").buildAndExpand(newList.getId()).toUri());
		return new ResponseEntity<>(newList, headers, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/lists")
	public TodoList updateList(@RequestBody @Valid TodoList todoList) {
		TodoList updatedList = service.update(todoList);
		updatedList.getUsers().clear();
		return updatedList;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') || (@userService.get(principal).get().id == #userId)")
	@GetMapping("/users/{userId}/lists")
	public Set<TodoList> getUserLists(@Min(1) @PathVariable long userId, Principal principal) {
		return service.getByUserId(userId).stream().map(list -> {
			list.getUsers().clear();
			return list;
		}).collect(Collectors.toSet());
	}
}
