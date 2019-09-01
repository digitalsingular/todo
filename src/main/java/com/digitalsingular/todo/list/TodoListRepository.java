package com.digitalsingular.todo.list;

import javax.validation.constraints.Min;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoListRepository extends PagingAndSortingRepository<TodoList, Long> {

	Iterable<TodoList> findByUsersId(@Min(1) long userId);

}
