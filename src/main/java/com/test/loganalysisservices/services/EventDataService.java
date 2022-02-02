package com.test.loganalysisservices.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.test.loganalysisservices.entities.EventData;
import com.test.loganalysisservices.modal.LogData;

public interface EventDataService {
	
	public Optional<List<LogData>> parseEventDataFromFile(String path) throws JsonParseException, JsonMappingException, IOException;
	public List<EventData> createEventDataAndSetAlertForPassedThreshold(String path) throws JsonParseException, JsonMappingException, IOException;
	public List<EventData> saveEventDataPassedThreshold(String path) throws JsonParseException, JsonMappingException, IOException;
	public List<EventData> getAllEventData();

}
