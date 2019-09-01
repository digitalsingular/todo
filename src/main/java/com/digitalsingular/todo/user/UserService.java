package com.digitalsingular.todo.user;

import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Sets;

@Service
@Transactional(readOnly = true)
@Validated
public class UserService implements UserDetailsService {

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

	public Optional<User> get(@NotBlank String login) {
		return repository.findByLogin(login);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " does not exist"));
	}
}
