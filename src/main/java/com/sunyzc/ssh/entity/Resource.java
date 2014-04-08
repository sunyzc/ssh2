package com.sunyzc.ssh.entity;

import java.io.Serializable;

public class Resource implements Serializable {
	private static final long serialVersionUID = -1699714574060625596L;
	private Long id;
	private String actionPath;
	private String ResourceName;
	private String description;
	private Authority authority;

	public Resource() {
	}

	public Resource(String actionPath, String resourceName) {
		this.actionPath = actionPath;
		ResourceName = resourceName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	public String getResourceName() {
		return ResourceName;
	}

	public void setResourceName(String resourceName) {
		ResourceName = resourceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
}
