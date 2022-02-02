package com.test.loganalysisservices.exceptions;

import org.springframework.http.HttpStatus;
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
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest req){
		
		ExceptionResponse response = new ExceptionResponse(ex.getMessage());	
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
	public static String prepareErrorLog(Exception ex) {
		return "error :" + ex.getStackTrace()[0].getClassName() + " : " + ex.getStackTrace()[0].getMethodName() + " : " + ex.getMessage();
	}
}
