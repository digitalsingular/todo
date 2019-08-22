package com.digitalsingular.todo.item;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoItemRepositoryIntegrationTest {

	@Autowired
	private TodoItemRepository sut;

	@Test
	public void givenANewTodoItemShouldPersistAndReturnId() {
		TodoItem item = new TodoItem("test");
		TodoItem persistedItem = sut.save(item);
		assertThat(item).isEqualTo(persistedItem);
		assertThat(persistedItem.getId()).isNotNull();
	}
}
