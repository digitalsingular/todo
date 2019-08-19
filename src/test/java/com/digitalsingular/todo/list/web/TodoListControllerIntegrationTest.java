package com.digitalsingular.todo.list.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.digitalsingular.todo.list.TodoListService;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoListController.class)
public class TodoListControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoListService service;

	@Test
	public void givenNoListsGetAllShouldReturnEmptySet() throws Exception {
		when(service.getAll()).thenReturn(Sets.newHashSet());
		mockMvc.perform(get("/lists").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$").isEmpty());
	}

}
