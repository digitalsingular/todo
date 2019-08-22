package com.digitalsingular.todo.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalsingular.todo.user.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoListRepositoryIntegrationTest {

	@Autowired
	private TodoListRepository sut;

	@Test
	public void givenANewListShouldPersistAndReturnIt() {
		TodoList list = new TodoList(new User("test", "test@test.com"), "Test List");
		TodoList persistedList = sut.save(list);
		assertThat(persistedList).isNotNull();
		assertThat(list).isEqualTo(persistedList);
	}
}
