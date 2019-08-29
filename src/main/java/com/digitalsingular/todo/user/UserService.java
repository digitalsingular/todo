package com.digitalsingular.todo.user;

import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Sets;

@Service
@Transactional(readOnly = true)
@Validated
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public Optional<User> get(@Min(1) long id) {
		return repository.findById(id);
	}

	public Set<User> getAll() {
		return Sets.newHashSet(repository.findAll());
	}
}
