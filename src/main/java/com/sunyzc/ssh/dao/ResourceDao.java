package com.sunyzc.ssh.dao;

import java.util.List;

import com.sunyzc.ssh.entity.Resource;

public interface ResourceDao extends BaseDao<Resource, Long> {
	public Resource getResourceByResourceName(String resourceName);

	public boolean isResourceNameUnique(String resourceName, String oldResourceName);

	public List<String> findAllActionPath();
}