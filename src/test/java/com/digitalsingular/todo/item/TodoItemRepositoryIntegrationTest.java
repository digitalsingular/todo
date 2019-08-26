package com.digitalsingular.todo.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;

import io.micrometer.core.instrument.util.StringUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoItemRepositoryIntegrationTest {

	@Autowired
	private TodoItemRepository sut;

	@Test
	public void givenOnePendingAndOneCompletedFindByStatusShouldFindOnlyOne() {
		TodoItem pending = new TodoItem("pending item");
		TodoItem completed = new TodoItem("completed item");
		completed.complete();
		sut.save(pending);
		sut.save(completed);
		Set<TodoItem> pendingItems = sut.findByStatus(ItemStatus.PENDING);
		assertThat(pendingItems.size()).isEqualTo(1);
		assertThat(pendingItems.contains(pending)).isTrue();
	}

	@Test
	public void givenOneTodoItemFindByDescriptionLikeShouldFindIt() {
		TodoItem item = new TodoItem("test");
		sut.save(item);
		Optional<TodoItem> persisted = sut.findByDescription("t");
		assertThat(persisted.isPresent()).isTrue();
	}

	@Test(expected = DataAccessException.class)
	public void givenTwoTodoItemsFindByDescriptionLikeShouldThrowException() {
		TodoItem firstItem = new TodoItem("test 1");
		TodoItem secondItem = new TodoItem("test 2");
		sut.save(firstItem);
		sut.save(secondItem);
		Optional<TodoItem> persisted = sut.findByDescription("t");
		assertThat(persisted.isPresent()).isTrue();
	}

	@Test
	public void givenOnePendingAndOneCompleteTodoItemFindAllOrderedByStatusShouldReturnFirstComplete() {
		TodoItem pending = new TodoItem("pending item");
		TodoItem completed = new TodoItem("completed item");
		completed.complete();
		sut.save(pending);
		sut.save(completed);
		Iterable<TodoItem> items = sut.findAll(Sort.by(Direction.ASC, "status"));
		assertThat(items.iterator().next().getStatus()).isEqualTo(ItemStatus.COMPLETE);
	}

	@Test
	public void givenTwoTodoItemsFindAllPagedWithPageSizeOneShouldReturnOne() {
		TodoItem pending = new TodoItem("pending item");
		TodoItem completed = new TodoItem("completed item");
		completed.complete();
		sut.save(pending);
		sut.save(completed);
		Page<TodoItem> items = sut.findAll(PageRequest.of(0, 1));
		assertThat(items.getSize()).isEqualTo(1);
	}

	@Test
	public void givenOnePendingAndOneCompleteTodoItemFindAllOrderedByStatusAndWithPageSizeOneShouldReturnFirstComplete() {
		TodoItem pending = new TodoItem("pending item");
		TodoItem completed = new TodoItem("completed item");
		completed.complete();
		sut.save(pending);
		sut.save(completed);
		Page<TodoItem> items = sut.findAll(PageRequest.of(0, 1, Direction.ASC, "status"));
		assertThat(items.getSize()).isEqualTo(1);
		assertThat(items.iterator().next().getStatus()).isEqualTo(ItemStatus.COMPLETE);
	}

	@Test
	public void givenOneTodoItemUpdateItsDescriptionShouldDoIt() {
		String newDescription = "new description";
		TodoItem item = new TodoItem("test");
		sut.save(item);
		int updatedItems = sut.updateDescription(newDescription, item.getId());
		assertThat(updatedItems).isEqualTo(1);
	}

	@Test
	public void givenThreeTodoItemsDeleteByDescriptionShouldDeleteTwoOfThem() {
		TodoItem firstItem = new TodoItem("first item");
		TodoItem secondItem = new TodoItem("second item");
		TodoItem thirdItem = new TodoItem("test");
		sut.save(firstItem);
		sut.save(secondItem);
		sut.save(thirdItem);
		int deletedItems = sut.deleteByDescription("item");
		assertThat(deletedItems).isEqualTo(2);
	}

	@Test
	public void givenATodoItemShouldFindItDinamically() {
		String descriptionSearch = "t";
		TodoItem item = new TodoItem("test");
		sut.save(item);
		QTodoItem qTodoItem = QTodoItem.todoItem;
		BooleanBuilder builder = new BooleanBuilder();
		if (!StringUtils.isEmpty(descriptionSearch)) {
			builder.and(qTodoItem.description.like("%" + descriptionSearch + "%"));
		}
		Iterable<TodoItem> items = sut.findAll(builder);
		assertThat(items.iterator().next()).isEqualTo(item);
	}
}
