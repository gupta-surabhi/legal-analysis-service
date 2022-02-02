package com.test.loganalysisservices.controllers;

import java.io.IOException;
import java.util.List;

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

@RestController
@RequestMapping("eventData")
public class EventDataController {

	@Autowired
	private EventDataService eventDataService;
	
	@PostMapping("/")
	public ResponseEntity<List<EventData>> saveEventDataPassedThreshold(@RequestBody String path)
			throws JsonParseException, JsonMappingException, IOException {
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
