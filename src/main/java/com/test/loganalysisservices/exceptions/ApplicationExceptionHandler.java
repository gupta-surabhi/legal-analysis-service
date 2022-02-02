package com.test.loganalysisservices.exceptions;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Exception> handleAllException(Exception ex, WebRequest req){
		
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage());
		logger.error(prepareErrorLog(ex));
		
		return ResponseEntity.of(Optional.of(response));
		
	}
	
	public static String prepareErrorLog(Exception ex) {
		return "error :" + ex.getStackTrace()[0].getClassName() + " : " + ex.getStackTrace()[0].getMethodName() + " : " + ex.getMessage();
	}
}
