package com.sunyzc.ssh.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Authority implements Serializable {
	private static final long serialVersionUID = 7148806369303995069L;
	private Long id;
	private String authorityName;
	private Authority parent;
	private String description;
	private Set<Authority> children = new HashSet<Authority>();
	private Set<Resource> resources = new HashSet<Resource>();
	private Set<Role> roles = new HashSet<Role>();

	public Authority() {
	}

	public Authority(String authorityName, Authority parent) {
		this.authorityName = authorityName;
		this.parent = parent;
	}

	public Authority(String authorityName, Authority parent, Set<Resource> resources) {
		this.authorityName = authorityName;
		this.parent = parent;
		this.resources = resources;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public Authority getParent() {
		return parent;
	}

	public void setParent(Authority parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Authority> getChildren() {
		return children;
	}

	public void setChildren(Set<Authority> children) {
		this.children = children;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
