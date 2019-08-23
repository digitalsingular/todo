package com.digitalsingular.todo.item;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoItemIntegrationTest {

	@Autowired
	private EntityManagerFactory emf;

	@Test
	public void givenANewTodoItemShouldPersistAndReturnId() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TodoItem item = new TodoItem("test");
		em.persist(item);
		TodoItem persistedItem = em.find(TodoItem.class, item.getId());
		tx.commit();
		em.close();
		assertThat(item.getId()).isNotNull();
		assertThat(persistedItem).isEqualTo(item);
	}

	@Test
	public void givenANewDescriptionShouldUpdateIt() {
		String newDescription = "new description";
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TodoItem item = new TodoItem("test");
		em.persist(item);
		item.setDescription(newDescription);
		em.merge(item);
		TodoItem persistedItem = em.find(TodoItem.class, item.getId());
		tx.commit();
		em.close();
		assertThat(item.getDescription()).isEqualTo(newDescription);
		assertThat(persistedItem).isEqualTo(item);
	}
}
