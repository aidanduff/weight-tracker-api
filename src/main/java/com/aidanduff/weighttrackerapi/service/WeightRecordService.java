package com.aidanduff.weighttrackerapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public WeightRecord addRecord(WeightRecord weightRecord) {
		return weightRecordRepository.save(weightRecord);
	}

	public List<WeightRecord> getAllWeightRecords() {
		List<WeightRecord> weightRecords = new ArrayList<>();
		
		weightRecordRepository.findAll()
			.forEach(weightRecords::add);
		
		return weightRecords;
	}

	public List<WeightRecord> getAllWeightRecordsByName(String name) {
		List<WeightRecord> weightRecords = new ArrayList<>();
		
		weightRecordRepository.findAllByUserName(name)
			.forEach(weightRecords::add);
		
		return weightRecords;
	}
	
	public WeightRecord getWeightRecord(long id) {
		return weightRecordRepository.findById(id).get();
	}
}
