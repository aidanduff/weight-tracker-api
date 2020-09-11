package com.aidanduff.weighttrackerapi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aidanduff.weighttrackerapi.model.WeightRecord;

public interface WeightRecordRepository extends CrudRepository<WeightRecord, Long> {

	List<WeightRecord> findAllByUserName(String name);


}
