package com.digitalsingular.todo.model.list;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/list")
public class TodoListController {

	@RequestMapping(method = RequestMethod.GET)
	public String todo() {
		return "Hello World!";
	}
}
