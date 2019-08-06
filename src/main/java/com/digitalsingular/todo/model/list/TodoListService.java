package com.digitalsingular.todo.model.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalsingular.todo.model.item.TodoItem;

@Service
public class TodoListService {

	@Autowired
	private TodoItem initialItem;

	@Autowired
	private TodoList initialList;

	public TodoList createList() {
		initialList.add(initialItem);
		return initialList;
	}

}
