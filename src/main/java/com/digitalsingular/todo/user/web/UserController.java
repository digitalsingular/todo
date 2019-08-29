package com.digitalsingular.todo.user.web;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalsingular.todo.user.User;
import com.digitalsingular.todo.user.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping
	public Set<User> getUsers() {
		return userService.getAll();
	}
}
