package com.digitalsingular.todo.model.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/list")
public class TodoListController {

	@Autowired
	public TodoListService todoListService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public String todo() {
		return todoListService.createList().toString();
	}
}
