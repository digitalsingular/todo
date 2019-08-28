package com.digitalsingular.todo.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/todo_lists.sql")
public class TodoListIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Test
	public void callingListsShouldReturnAllOfThem() {
		ResponseEntity<String> response = template.getForEntity("/lists", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String lists = response.getBody();
		JSONArray jsonArray = JsonPath.read(lists, "$");
		assertThat(jsonArray.size()).isGreaterThan(0);
	}

	@Test
	public void callingListsWithIdShouldReturnIt() {
		ResponseEntity<String> response = template.getForEntity("/lists", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String lists = response.getBody();
		Long listId = JsonPath.parse(lists).read("$[0]['id']", Long.class);
		assertThat(listId).isGreaterThan(0);
		response = template.getForEntity("/lists/" + listId, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String list = response.getBody();
		Long retrievedListId = JsonPath.parse(list).read("$['id']", Long.class);
		assertThat(retrievedListId).isEqualTo(listId);
	}

	@Test
	public void callingListsWithInvalidIdShouldReturnBadRequest() {
		ResponseEntity<String> response = template.getForEntity("/lists/a", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void callingListsWithInvalidIdShouldReturnNotFound() {
		ResponseEntity<String> response = template.getForEntity("/lists/-1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void postListsWithValidPayloadShouldReturnNewTodoList() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject jsonList = new JSONObject();
		jsonList.put("description", "Integration tests");
		HttpEntity<String> entity = new HttpEntity<>(jsonList.toString(), headers);
		ResponseEntity<String> response = template.postForEntity("/lists", entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Long retrievedListId = JsonPath.parse(response.getBody()).read("$['id']", Long.class);
		assertThat(retrievedListId).isGreaterThan(0);
	}

	@Test
	public void postListsWithInvalidPayloadShouldReturnBadRequest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Integer> entity = new HttpEntity<>(0, headers);
		ResponseEntity<String> response = template.postForEntity("/lists", entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void putListsWithValidPayloadShouldReturnUpdatedTodoList() throws JSONException {
		ResponseEntity<String> response = template.getForEntity("/lists", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String lists = response.getBody();
		Long listId = JsonPath.parse(lists).read("$[0]['id']", Long.class);
		assertThat(listId).isGreaterThan(0);
		JSONObject jsonList = new JSONObject();
		jsonList.put("id", listId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(jsonList.toString(), headers);
		response = template.exchange("/lists", HttpMethod.PUT, entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void putListsWithInvalidIdShouldReturnNotFound() throws JSONException {
		JSONObject jsonList = new JSONObject();
		jsonList.put("id", -1);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(jsonList.toString(), headers);
		ResponseEntity<String> response = template.exchange("/lists", HttpMethod.PUT, entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
