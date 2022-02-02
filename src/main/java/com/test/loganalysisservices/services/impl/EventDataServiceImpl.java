package com.test.loganalysisservices.services.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.loganalysisservices.entities.EventData;
import com.test.loganalysisservices.exceptions.ApplicationExceptionHandler;
import com.test.loganalysisservices.modal.LogData;
import com.test.loganalysisservices.repositories.EventDataRepository;
import com.test.loganalysisservices.services.EventDataService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventDataServiceImpl implements EventDataService {

	@Autowired
	private EventDataRepository eventDataRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger logger = LoggerFactory.getLogger(EventDataServiceImpl.class);

	@Override
	public Optional<List<LogData>> parseEventDataFromFile(String path)
			throws JsonParseException, JsonMappingException, IOException {

		try (BufferedInputStream bufferInputStream = new BufferedInputStream(new FileInputStream(path));
				BufferedReader bufferReader = new BufferedReader(
						new InputStreamReader(bufferInputStream, StandardCharsets.UTF_8))) {
			List<LogData> logData = new ArrayList<>();
			bufferReader.lines().forEach(jsonValue -> {
				try {
					logData.add(objectMapper.readValue(jsonValue, LogData.class));
				} catch (JsonProcessingException e) {
					logger.error(ApplicationExceptionHandler.prepareErrorLog(e));
				}
			});
			return Optional.of(logData);
		} catch (IOException ex) {
			logger.error(ApplicationExceptionHandler.prepareErrorLog(ex));
			throw ex;
		}
	}

	@Override
	public List<EventData> createEventDataAndSetAlertForPassedThreshold(String path)
			throws JsonParseException, JsonMappingException, IOException {
		
		Optional<List<LogData>> logData = parseEventDataFromFile(path);
		
		if (logData.isPresent()) {

			List<EventData> eventDataList = new ArrayList<>();

			Map<String, List<LogData>> logs = logData.get().stream() // .sorted(Comparator.comparing(LogData::getTimeStamp).reversed())
					.collect(Collectors.groupingBy(LogData::getId,
							Collectors.mapping(Function.identity(), Collectors.toList())));

			logs.entrySet().forEach(log -> {
				List<LogData> logDataList = log.getValue();
				EventData eventData = objectMapper.convertValue(logDataList.get(0), EventData.class);

				eventData.setEventId(log.getKey());

				if (logDataList.size() == 2) {
					Long duration = Math.abs(Math.subtractExact(Long.valueOf(logDataList.get(0).getTimestamp()),
							Long.valueOf(logDataList.get(1).getTimestamp())));

					eventData.setEventDuration(duration.toString());

					if (duration > 4)
						eventData.setAlert(true);
				}

				eventDataList.add(eventData);
			});

			return eventDataList;
		}
		return null;
	}

	@Override
	public List<EventData> saveEventDataPassedThreshold(String path)
			throws JsonParseException, JsonMappingException, IOException {

		List<EventData> eventDataList = createEventDataAndSetAlertForPassedThreshold(path);

		if (null != eventDataList)
			return eventDataRepository.saveAll(eventDataList);

		return null;
	}

	@Override
	public List<EventData> getAllEventData() {
		return eventDataRepository.findAll();
	}

}
