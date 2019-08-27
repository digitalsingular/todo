package com.digitalsingular.todo.list;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@Sql("/todo_lists.sql")
public class TodoListIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Test
	public void callingListsShouldReturnAllOfThem() throws JSONException {
		ResponseEntity<String> response = template.getForEntity("/lists", String.class);
		String lists = response.getBody();
		String expected = "[{\"id\":1,\"description\":\"Integration tests\",\"users\":[],\"numberOfPending\":0,\"pending\":[],\"completed\":[],\"numberOfCompleted\":0}]";
		JSONAssert.assertEquals(expected, lists, false);
	}
}
