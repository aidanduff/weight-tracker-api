package com.aidanduff.weighttrackerapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.aidanduff.weighttrackerapi.model.WeightRecord;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTests {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void itemShouldBeCreated() throws Exception {
		WeightRecord weightRecord = new WeightRecord("Joe Bloggs", 100.5);
		HttpEntity<WeightRecord> request = new HttpEntity<>(weightRecord);
		ResponseEntity<WeightRecord> result = this.restTemplate.postForEntity("http://localhost:" + port + "/add", request, WeightRecord.class);
		assertEquals(200, result.getStatusCodeValue());
	}

}
