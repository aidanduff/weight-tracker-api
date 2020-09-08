package com.aidanduff.weighttrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aidanduff.weighttrackerapi.service.WeightRecordService;

@RestController
public class WeightRecordController {
	
	@Autowired
	WeightRecordService weightRecordService;
	
	@GetMapping("/")
	public String welcome() {
		return "<h1>" + weightRecordService.welcome() +"</h1>";
	}

}
