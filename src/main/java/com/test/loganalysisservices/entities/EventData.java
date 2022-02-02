package com.test.loganalysisservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "EventData", catalog = "Test")
@Entity
@Data
@EqualsAndHashCode
public class EventData {

	@Id
	@Column(name = "EventId")
	private String eventId;

	@Column(name = "Alert")
	private boolean alert;

	@Column(name = "Type")
	private String type;

	@Column(name = "Host")
	private String host;

	@Column(name = "EventDuration")
	private String eventDuration;

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the alert
	 */
	public boolean getAlert() {
		return alert;
	}

	/**
	 * @param alert the alert to set
	 */
	public void setAlert(boolean alert) {
		this.alert = alert;
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
	 * @return the eventDuration
	 */
	public String getEventDuration() {
		return eventDuration;
	}

	/**
	 * @param eventDuration the eventDuration to set
	 */
	public void setEventDuration(String eventDuration) {
		this.eventDuration = eventDuration;
	}
	
	

}
