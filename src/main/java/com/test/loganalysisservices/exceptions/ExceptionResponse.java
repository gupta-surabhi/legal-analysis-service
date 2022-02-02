package com.test.loganalysisservices.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
public class ExceptionResponse  extends RuntimeException{
	
	private LocalDateTime date;
	private String message;	
	
	public ExceptionResponse(String message) {
		super(message);
	}
	public ExceptionResponse(LocalDateTime date, String message) {
		super(message);
		this.date = date;
		this.message = message;
	}
	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}	
}

