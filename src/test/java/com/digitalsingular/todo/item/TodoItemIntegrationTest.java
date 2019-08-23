package com.digitalsingular.todo.item;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	@Test
	public void givenATodoItemShouldRemoveIt() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TodoItem item = new TodoItem("test");
		em.persist(item);
		em.remove(item);
		tx.commit();
		TodoItem persistedItem = em.find(TodoItem.class, item.getId());
		em.close();
		assertThat(persistedItem).isNull();
	}

	@Test
	public void givenATodoItemShouldSelectItWithJpql() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TodoItem item = new TodoItem("test");
		em.persist(item);
		Query query = em.createQuery("select todoItem from TodoItem todoItem where todoItem.id = :id");
		query.setParameter("id", item.getId());
		TodoItem persistedItem = (TodoItem) query.getSingleResult();
		assertThat(persistedItem).isEqualTo(item);
	}

	@Test
	public void givenATodoItemShouldSelectItWithCriteria() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TodoItem item = new TodoItem("test");
		em.persist(item);
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TodoItem> criteriaQuery = criteriaBuilder.createQuery(TodoItem.class);
		Root<TodoItem> itemRoot = criteriaQuery.from(TodoItem.class);
		criteriaQuery.where(criteriaBuilder.equal(itemRoot.get("id"), criteriaBuilder.parameter(Long.class, "id")));
		Query query = em.createQuery(criteriaQuery);
		query.setParameter("id", item.getId());
		TodoItem persistedItem = (TodoItem) query.getSingleResult();
		assertThat(persistedItem).isEqualTo(item);
	}

	@Test
	public void givenATodoItemShouldSelectItWithSql() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TodoItem item = new TodoItem("test");
		em.persist(item);
		Query query = em.createNativeQuery("select * from TODO_ITEMS where id = :id", TodoItem.class);
		query.setParameter("id", item.getId());
		TodoItem persistedItem = (TodoItem) query.getSingleResult();
		assertThat(persistedItem).isEqualTo(item);
	}
}
