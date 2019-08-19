package com.digitalsingular.todo.list.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;

import com.digitalsingular.todo.list.TodoList;
import com.digitalsingular.todo.list.TodoListService;

public class TodoListControllerTest {

	private TodoListController sut;

	private TodoListService service;

	@Before
	public void setUp() {
		service = mock(TodoListService.class);
		sut = new TodoListController(service);
	}

	@Test
	public void givenNoListsGetAllShouldReturnEmptySet() {
		when(service.getAll()).thenReturn(Sets.newHashSet());
		assertThat(sut.getAllLists()).isEmpty();
	}

	@Test
	public void givenOneListGetAllShouldReturnIt() {
		TodoList list = new TodoList("test");
		when(service.getAll()).thenReturn(Sets.newLinkedHashSet(list));
		assertThat(sut.getAllLists()).contains(list);
	}

}
