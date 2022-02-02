package com.test.loganalysisservices.modal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LogData {
	private String id;
	private String state;
	private String type;
	private String host;
	private String timestamp;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the timeStamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimestamp(String timeStamp) {
		this.timestamp = timeStamp;
	}
	
	
}
