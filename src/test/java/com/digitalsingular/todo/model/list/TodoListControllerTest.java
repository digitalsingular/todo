package com.digitalsingular.todo.model.list;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class TodoListControllerTest {

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void givenGetToListShouldReturnList() {
		System.out.println(restTemplate.getForObject("http://localhost:8080/todo/list", String.class));
	}

	@Test
	public void givenPutToListShouldReturnListWithItem() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String resourceUrl = "http://localhost:8080/todo/list";
		HttpEntity<String> requestUpdate = new HttpEntity<>("nuevo item", headers);
		restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
		System.out.println(restTemplate.getForObject("http://localhost:8080/todo/list", String.class));
	}
}
