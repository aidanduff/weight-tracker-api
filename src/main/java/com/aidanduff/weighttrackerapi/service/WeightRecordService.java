package com.aidanduff.weighttrackerapi.service;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void addRecord(WeightRecord weightRecord) {
		System.out.println(weightRecord.getUserName());
		weightRecordRepository.save(weightRecord);
	}
}
