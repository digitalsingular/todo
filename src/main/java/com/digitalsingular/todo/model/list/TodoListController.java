package com.digitalsingular.todo.model.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalsingular.todo.model.item.TodoItem;

@RestController
@RequestMapping(value = "/list")
public class TodoListController {

	@Autowired
	private TodoListService todoListService;

	@GetMapping
	public String list() {
		return todoListService.getList().toString();
	}

	@PutMapping
	public void addItem(@RequestBody String description) {
		todoListService.addItem(new TodoItem(description));
	}
}
