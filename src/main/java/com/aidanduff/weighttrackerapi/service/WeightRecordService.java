package com.aidanduff.weighttrackerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aidanduff.weighttrackerapi.dao.WeightRecordRepository;
import com.aidanduff.weighttrackerapi.model.WeightRecord;

@Service
public class WeightRecordService {
	
	@Autowired
	WeightRecordRepository weightRecordRepository;
	
	public String welcome() {
		return "Welcome";
	}

	public WeightRecord addRecord(WeightRecord weightRecord) {
		System.out.println(weightRecord.getUserName());
		return weightRecordRepository.save(weightRecord);
	}
}
