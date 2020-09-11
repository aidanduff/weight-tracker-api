package com.aidanduff.weighttrackerapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
	public void postNewWeightRecordShouldSucceed() throws Exception {
		WeightRecord weightRecord = new WeightRecord("JoeBloggs", 65.5);
		String json = new ObjectMapper().writeValueAsString(weightRecord);
		
		when(weightRecordService.addRecord(weightRecord))
			.thenReturn(weightRecord);
		
		this.mockMvc.perform(post("/weightRecords")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json)
	            .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isCreated());
	}
	
	@Test
	public void listShouldbeReturnedFromGetAll() throws Exception {
		List<WeightRecord> weightRecordList = new ArrayList<>();
		weightRecordList.add(new WeightRecord("JoeBloggs", 65.5));
		when(weightRecordService.getAllWeightRecords())
				.thenReturn(weightRecordList);
		
		this.mockMvc.perform(get("/weightRecords"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));	
		
		assertEquals(ArrayList.class, weightRecordController.getAllWeightRecords().getBody().getClass());
		assertEquals(1, weightRecordController.getAllWeightRecords().getBody().size());
	}
	
	@Test
	public void listShouldbeReturnedFromGetAllByUser() throws Exception {
		List<WeightRecord> weightRecordList = new ArrayList<>();
		weightRecordList.add(new WeightRecord("JoeBloggs", 65.5));
		weightRecordList.add(new WeightRecord("JoeBloggs", 65.0));
		when(weightRecordService.getAllWeightRecordsByName("JoeBloggs"))
				.thenReturn(weightRecordList);
		
		this.mockMvc.perform(get("/weightRecords/JoeBloggs"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));	
		
		assertEquals(ArrayList.class, weightRecordController.getAllWeightRecordsByName("JoeBloggs").getBody().getClass());
	}
	

}
