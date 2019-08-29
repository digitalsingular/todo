package com.digitalsingular.todo.list;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.json.JSONArray;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/todo_lists.sql")
public class TodoListIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Test
	public void getListsShouldReturnAllOfThem() {
		ResponseEntity<String> response = template.getForEntity("/lists", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String lists = response.getBody();
		int numberOfLists = JsonPath.parse(lists).read("$.length()", Integer.class);
		assertThat(numberOfLists).isGreaterThan(0);
	}

	@Test
	public void getListsWithIdShouldReturnIt() throws JSONException {
		Long listId = getExistingList().getLong("id");
		ResponseEntity<String> response = template.getForEntity("/lists/" + listId, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String list = response.getBody();
		Long retrievedListId = JsonPath.parse(list).read("$['id']", Long.class);
		assertThat(retrievedListId).isEqualTo(listId);
	}

	@Test
	public void getListsWithInvalidIdShouldReturnBadRequest() {
		ResponseEntity<String> response = template.getForEntity("/lists/a", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void getListsWithNonExistingIdShouldReturnNotFound() {
		ResponseEntity<String> response = template.getForEntity("/lists/" + Integer.MAX_VALUE, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void postListsWithValidTodoListShouldReturnNewTodoList() throws JSONException {
		JSONObject jsonList = new JSONObject();
		jsonList.put("description", "Integration tests");
		ResponseEntity<String> response = post(jsonList);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Long retrievedListId = JsonPath.parse(response.getBody()).read("$['id']", Long.class);
		assertThat(retrievedListId).isGreaterThan(0);
	}

	@Test
	public void postListsWithInvalidTodoListShouldReturnBadRequest() throws JSONException {
		JSONObject jsonList = new JSONObject();
		jsonList.put("description", "");
		ResponseEntity<String> response = post(jsonList);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void postListWithInvalidUserShouldReturnBadRequest() throws JSONException {
		JSONObject jsonList = new JSONObject();
		jsonList.put("description", "Integration tests");
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id", -1);
		JSONArray jsonUserArray = new JSONArray();
		jsonUserArray.put(jsonUser);
		jsonList.put("users", jsonUserArray);
		ResponseEntity<String> response = post(jsonList);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void postListWithValidUserShouldReturnListWithUser() throws JSONException {
		JSONObject jsonList = new JSONObject();
		jsonList.put("description", "Integration tests");
		JSONObject jsonUser = getExistingUser();
		JSONArray jsonUserArray = new JSONArray();
		jsonUserArray.put(jsonUser);
		jsonList.put("users", jsonUserArray);
		ResponseEntity<String> response = post(jsonList);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	public void postListsWithInvalidPayloadShouldReturnBadRequest() {
		HttpEntity<Integer> entity = new HttpEntity<>(0, getJsonHeaders());
		ResponseEntity<String> response = template.postForEntity("/lists", entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void putListsWithValidTodoListShouldReturnUpdatedTodoList() throws JSONException {
		JSONObject jsonList = getExistingList();
		ResponseEntity<String> response = put(jsonList);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void putListsWithInvalidIdShouldReturnNotFound() throws JSONException {
		JSONObject jsonList = getExistingList();
		jsonList.put("id", Integer.MAX_VALUE);
		ResponseEntity<String> response = put(jsonList);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void putListsWithInvalidTodoListShouldReturnBadRequest() throws JSONException {
		JSONObject jsonList = getExistingList();
		jsonList.put("description", "");
		ResponseEntity<String> response = put(jsonList);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	private HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	private ResponseEntity<String> post(JSONObject jsonList) {
		HttpEntity<String> entity = new HttpEntity<>(jsonList.toString(), getJsonHeaders());
		ResponseEntity<String> response = template.postForEntity("/lists", entity, String.class);
		return response;
	}

	private JSONObject getExistingUser() throws JSONException {
		ResponseEntity<String> response = template.getForEntity("/users", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String users = response.getBody();
		Long userId = JsonPath.parse(users).read("$[0]['id']", Long.class);
		assertThat(userId).isGreaterThan(0);
		Map<String, String> user = JsonPath.parse(users).read("$[0]");
		JSONObject jsonUser = new JSONObject(user);
		return jsonUser;
	}

	private JSONObject getExistingList() throws JSONException {
		ResponseEntity<String> response = template.getForEntity("/lists", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String lists = response.getBody();
		Long listId = JsonPath.parse(lists).read("$[0]['id']", Long.class);
		assertThat(listId).isGreaterThan(0);
		Map<String, String> list = JsonPath.parse(lists).read("$[0]");
		JSONObject jsonList = new JSONObject(list);
		return jsonList;
	}

	private ResponseEntity<String> put(JSONObject jsonList) {
		HttpEntity<String> entity = new HttpEntity<>(jsonList.toString(), getJsonHeaders());
		ResponseEntity<String> response = template.exchange("/lists", HttpMethod.PUT, entity, String.class);
		return response;
	}
}
