package com.digitalsingular.todo.model.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.digitalsingular.todo.AppConfiguration;
import com.digitalsingular.todo.TestAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfiguration.class, TestAppConfiguration.class })
@ActiveProfiles("test")
public class TodoListControllerTest {

	@Autowired
	private TodoListController controller;

	@Test
	public void givenCallListShouldReturnEmptyList() {
		String list = controller.list();
		assertThat(list).isNotBlank();
	}

	@Test
	public void givenAValidDescriptionAddItemShouldAddIt() {
		String description = "description";
		controller.addItem(description);
		assertThat(controller.list()).contains(description);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenANullDescriptionAddItemShouldThrowException() {
		controller.addItem(null);
	}
}
