package com.aidanduff.weighttrackerapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.aidanduff.weighttrackerapi.model.WeightRecord;

public interface WeightRecordRepository extends CrudRepository<WeightRecord, Long> {


}
