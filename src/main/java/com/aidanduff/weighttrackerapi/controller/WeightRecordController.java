package com.aidanduff.weighttrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aidanduff.weighttrackerapi.model.WeightRecord;
import com.aidanduff.weighttrackerapi.service.WeightRecordService;

@RestController
public class WeightRecordController {
	
	@Autowired
	WeightRecordService weightRecordService;
	
	@GetMapping("/")
	public String welcome() {
		return "<h1>" + weightRecordService.welcome() +"</h1>";
	}
	
	@PostMapping("/add")
	public void addRecord(@RequestBody WeightRecord weightRecord) {
		System.out.println(weightRecord.getUserName());
		weightRecordService.addRecord(weightRecord);
	}
	
	@GetMapping("/weightRecords")
	public ResponseEntity<List<WeightRecord>> getAllWeightRecords() {
		List<WeightRecord> weightRecords = weightRecordService.getAllWeightRecords();
		return weightRecords.size() > 0? new ResponseEntity<>(weightRecords, HttpStatus.OK): ResponseEntity.noContent().build();
	}
	
	@GetMapping("/weightRecords/{name}")
	public ResponseEntity<List<WeightRecord>> getAllWeightRecordsByName(@PathVariable String name) {
		List<WeightRecord> weightRecords = weightRecordService.getAllWeightRecordsByName(name);
		return weightRecords.size() > 0? new ResponseEntity<>(weightRecords, HttpStatus.OK): ResponseEntity.noContent().build();
	}

}
