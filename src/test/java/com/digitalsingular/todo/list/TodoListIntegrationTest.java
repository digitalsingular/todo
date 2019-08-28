package com.digitalsingular.todo.list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
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
	public void callingListsWithInvalidIdShouldReturn404() {
		ResponseEntity<String> response = template.getForEntity("/lists/-1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
