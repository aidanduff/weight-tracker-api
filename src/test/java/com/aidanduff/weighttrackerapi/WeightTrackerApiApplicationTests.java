package com.aidanduff.weighttrackerapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aidanduff.weighttrackerapi.controller.WeightRecordController;
import com.aidanduff.weighttrackerapi.service.WeightRecordService;

@SpringBootTest
class WeightTrackerApiApplicationTests {

	@Autowired
	private WeightRecordService weightRecordService;
	
	@Autowired
	private WeightRecordController weightRecordController;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void weightRecordControllerLoads() throws Exception {
		assertThat(weightRecordController).isNotNull();
	}
	
	@Test
	public void weightRecordServiceLoads() throws Exception {
		assertThat(weightRecordService).isNotNull();
	}	

}
