package com.sunyzc.ssh.entity;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
	private static final long serialVersionUID = 1136553775930064643L;
	private Long id;
	private User user;
	private Date occurDate;
	private String operation;
	private String description;

	public Log() {
	}

	public Log(User user, Date occurDate, String operation) {
		this.user = user;
		this.occurDate = occurDate;
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}