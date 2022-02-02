package com.test.loganalysisservice.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.loganalysisservices.entities.EventData;
import com.test.loganalysisservices.modal.LogData;
import com.test.loganalysisservices.repositories.EventDataRepository;
import com.test.loganalysisservices.services.impl.EventDataServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class EventDataService {

	@InjectMocks
	private EventDataServiceImpl eventDataService;

	@Mock
	private ObjectMapper mapper;

	@Mock
	private EventDataRepository eventDataRepository;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void saveEventDataTest() throws JsonParseException, JsonMappingException, IOException {
		String filePath = new ClassPathResource("logfile.txt").getURI().getPath();
		when(mapper.readValue(Mockito.anyString(), eq(LogData.class))).thenReturn(createLogData("a", "10"),
				createLogData("a", "1"));
		when(mapper.convertValue(Mockito.any(LogData.class), eq(EventData.class))).thenReturn(new EventData());
		List<EventData> eventData = eventDataService.saveEventDataPassedThreshold(filePath);
		assertNotNull(eventData);
	}

	@Test
	public void parseEventDataFromFileTest() throws JsonParseException, JsonMappingException, IOException {
		String filePath = new ClassPathResource("logfile.txt").getURI().getPath();
		when(mapper.readValue(Mockito.anyString(), eq(LogData.class))).thenReturn(createLogData("a", "10"),
				createLogData("a", "1"));
		when(mapper.convertValue(Mockito.any(LogData.class), eq(EventData.class))).thenReturn(new EventData());
		List<EventData> eventData = eventDataService.createEventDataAndSetAlertForPassedThreshold(filePath);
		assertNotNull(eventData);
		assertEquals(1, eventData.size());
	}

	@Test
	public void parseEventDataFNFTest() throws JsonParseException, JsonMappingException, IOException {

		assertThatThrownBy(() -> eventDataService.createEventDataAndSetAlertForPassedThreshold("anyPath"))
				.isInstanceOf(FileNotFoundException.class);
	}

	@Test
	public void parseEventDataFromFileNullPathTest() throws JsonParseException, JsonMappingException, IOException {
		List<EventData> eventData = eventDataService.createEventDataAndSetAlertForPassedThreshold(null);
		assertNull(eventData);
	}

	public LogData createLogData(String id, String time) {
		LogData logData = new LogData();
		logData.setId(id);
		logData.setTimestamp(time);
		return logData;
	}

}
