package com.aidanduff.weighttrackerapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
	public void weightRecordShouldBeCreated() throws Exception {
		WeightRecord weightRecord = new WeightRecord("JoeBloggs", 100.5);
		HttpEntity<WeightRecord> request = new HttpEntity<>(weightRecord);
		ResponseEntity<WeightRecord> result = this.restTemplate.postForEntity("http://localhost:" + port + "/weightRecords", request, WeightRecord.class);
		assertEquals(201, result.getStatusCodeValue());
	}
	
	@Test
	public void allWeightRecordsShouldBeRetrieved() throws Exception {
		WeightRecord weightRecord = new WeightRecord("Joe Bloggs", 100.5);
		HttpEntity<WeightRecord> request = new HttpEntity<>(weightRecord);
		this.restTemplate.postForEntity("http://localhost:" + port + "/weightRecords", request, WeightRecord.class);
		
		WeightRecord weightRecord2 = new WeightRecord("Joe Bloggs", 100.5);
		HttpEntity<WeightRecord> request2 = new HttpEntity<>(weightRecord2);
		this.restTemplate.postForEntity("http://localhost:" + port + "/weightRecords", request2, WeightRecord.class);
		
		ResponseEntity<List> result = this.restTemplate.getForEntity("http://localhost:" + port + "/weightRecords", List.class);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void allWeightRecordsShouldBeRetrievedForGivenUser() throws Exception {
		WeightRecord weightRecord = new WeightRecord("JoeBloggs", 100.5);
		HttpEntity<WeightRecord> request = new HttpEntity<>(weightRecord);
		this.restTemplate.postForEntity("http://localhost:" + port + "/weightRecords", request, WeightRecord.class);
		
		WeightRecord weightRecord2 = new WeightRecord("JoeBloggs", 100.6);
		HttpEntity<WeightRecord> request2 = new HttpEntity<>(weightRecord2);
		this.restTemplate.postForEntity("http://localhost:" + port + "/weightRecords", request2, WeightRecord.class);
		
		ResponseEntity<List> result = this.restTemplate.getForEntity("http://localhost:" + port + "/weightRecords/JoeBloggs", List.class);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void weightRecordShouldBeRetrievedById() throws Exception {
		WeightRecord weightRecord = new WeightRecord("JaneDoe", 85.0);
		HttpEntity<WeightRecord> request = new HttpEntity<>(weightRecord);
		ResponseEntity<WeightRecord> response = this.restTemplate.postForEntity("http://localhost:" + port + "/weightRecords", request, WeightRecord.class);
		ResponseEntity<WeightRecord> result = this.restTemplate.getForEntity("http://localhost:" + port + "/weightRecord/" + response.getBody().getId() + "", WeightRecord.class);
		assertEquals(302, result.getStatusCodeValue());
	}

}
