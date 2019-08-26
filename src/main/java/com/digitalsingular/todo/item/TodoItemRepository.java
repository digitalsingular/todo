package com.digitalsingular.todo.item;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TodoItemRepository
extends PagingAndSortingRepository<TodoItem, Long>, QuerydslPredicateExecutor<TodoItem>{

	Set<TodoItem> findByStatus (ItemStatus status);

	@Query("select ti from TodoItem ti where ti.description like %:description%")
	Optional<TodoItem> findByDescription(@Param("description") String description);

	@Modifying
	@Query("update TodoItem ti set ti.description = :description where ti.id = :id")
	int updateDescription(@Param("description") String description, @Param("id") long id);

	@Modifying
	@Query("delete TodoItem ti where ti.description like %:description%")
	int deleteByDescription(String description);
}
