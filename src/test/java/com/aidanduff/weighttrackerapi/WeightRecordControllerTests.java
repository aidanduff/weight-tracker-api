package com.aidanduff.weighttrackerapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
		
		MvcResult result = this.mockMvc.perform(post("/weightRecords")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(json)
	            .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
		
		assertEquals(WeightRecord.class, weightRecordController.addRecord(weightRecord).getBody().getClass());
		
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
	
	@Test
	public void getWeightRecordShouldSucceed() throws Exception {
		WeightRecord weightRecord = new WeightRecord("BillBrown", 80.0);
		weightRecord.setId(1L);

		when(weightRecordService.addRecord(weightRecord)).thenReturn(weightRecord);
		when(weightRecordService.getWeightRecord(1)).thenReturn(weightRecord);
		
		this.mockMvc.perform(get("/weightRecord/1")
				.contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isFound())
				.andExpect(content().json("{'userName': 'BillBrown'}"))
				.andExpect(content().json("{'weight': 80.0}"));
	}
	
	@Test
	public void deleteWeightRecordShouldSucceed() throws Exception {
		WeightRecord weightRecord = new WeightRecord("Neesha", 15.0);

		when(weightRecordService.deleteWeightRecord(100)).thenReturn(weightRecord);
		weightRecord.setId(100L);
				
		this.mockMvc.perform(delete("/weightRecord/100")
				.contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
				.andDo(print())
                .andExpect(status().isNoContent());
	}
	
	

}
