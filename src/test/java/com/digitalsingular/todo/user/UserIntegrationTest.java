package com.digitalsingular.todo.user;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalsingular.todo.list.TodoList;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserIntegrationTest {

	@Autowired
	private EntityManagerFactory emf;

	@Test
	public void givenANewUserShouldPersistAndReturnIt() {
		User user = new User("test", "test@test.com");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(user);
		User persistedUser = em.find(User.class, user.getId());
		assertThat(user).isEqualTo(persistedUser);
		assertThat(persistedUser.getId()).isNotNull();
		tx.commit();
		em.close();
	}

	@Test
	public void givenANewUserWithAListShouldPersistAndReturnUserAndList() {
		User user = new User("test", "test@test.com");
		user.addList(new TodoList("test list"));
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(user);
		User persistedUser = em.find(User.class, user.getId());
		assertThat(user).isEqualTo(persistedUser);
		assertThat(persistedUser.getId()).isNotNull();
		persistedUser.getLists().stream().forEach(list -> assertThat(list.getId()).isNotNull());
		tx.commit();
		em.close();
	}
}
