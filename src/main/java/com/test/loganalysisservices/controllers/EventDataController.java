package com.test.loganalysisservices.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.test.loganalysisservices.entities.EventData;
import com.test.loganalysisservices.services.EventDataService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("eventData")
public class EventDataController {

	@Autowired
	private EventDataService eventDataService;
	
	/*
	 * @Tag(description =
	 * "Accepts log file path, load log from it and return threshhold exceeded event data"
	 * , name = "Service Description")
	 * 
	 * @ApiResponse(description = "return threshhold exceeded event data")
	 */
	@ApiOperation(value = "Accepts log file path, load log from it and return threshhold exceeded event data")
	@PostMapping("/")
	public ResponseEntity<List<EventData>> saveEventDataPassedThreshold(@RequestBody String path)
			throws JsonParseException, JsonMappingException, IOException {
		if(StringUtils.isBlank(path))
			return ResponseEntity.notFound().build();
		
		List<EventData> eventData = eventDataService.saveEventDataPassedThreshold(path);

		if (null == eventData)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(eventData);

	}
	
	@GetMapping("/")
	public ResponseEntity<List<EventData>> getAllEventData(){
		List<EventData> eventData = eventDataService.getAllEventData();

		if (null == eventData)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(eventData);

	}
}
