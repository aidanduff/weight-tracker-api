package com.aidanduff.weighttrackerapi;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.aidanduff.weighttrackerapi.controller.WeightRecordController;
import com.aidanduff.weighttrackerapi.model.WeightRecord;
import com.aidanduff.weighttrackerapi.service.WeightRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@RunWith(SpringRunner.class)
public class WeightRecordControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WeightRecordService weightRecordService;
	
	@Autowired
	private WeightRecordController weightRecordController;
	
	@Test
	public void postNewItemShouldSucceed() throws Exception {
		WeightRecord weightRecord = new WeightRecord("Joe Bloggs", 65.5);
		String json = new ObjectMapper().writeValueAsString(weightRecord);
		
		when(weightRecordService.addRecord(weightRecord))
			.thenReturn(weightRecord);
		
		this.mockMvc.perform(post("/add")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json)
	            .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isOk());
	}
	

}
