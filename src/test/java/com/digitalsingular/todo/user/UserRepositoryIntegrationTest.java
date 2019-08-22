package com.digitalsingular.todo.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private UserRepository sut;

	@Test
	public void givenANewUserShouldPersistAndReturnIt() {
		User user = new User("test", "test@test.com");
		User persistedUser = sut.save(user);
		assertThat(user).isEqualTo(persistedUser);
		assertThat(persistedUser.getId()).isNotNull();
	}

	@Test
	public void givenANewUserWithAListShouldPersistAndReturnUserAndList() {
		User user = new User("test", "test@test.com");
		user.addList("test list");
		User persistedUser = sut.save(user);
		assertThat(user).isEqualTo(persistedUser);
		assertThat(persistedUser.getId()).isNotNull();
		assertThat(persistedUser.getLists()).isEqualTo(user.getLists());
		persistedUser.getLists().stream().forEach(list -> assertThat(list.getId()).isNotNull());
	}
}
