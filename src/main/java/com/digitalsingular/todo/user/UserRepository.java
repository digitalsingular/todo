package com.digitalsingular.todo.user;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Optional<User> findByLogin(String login);

}
