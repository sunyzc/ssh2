package com.sunyzc.ssh.service;

import java.util.List;

import com.sunyzc.ssh.dao.BaseDao;
import com.sunyzc.ssh.entity.Resource;

public interface ResourceService extends BaseDao<Resource, Long> {
	public Resource getResourceByResourceName(String resourceName);

	public boolean isResourceNameUnique(String resourceName, String oldResourceName);

	public List<String> findAllActionPath();
}
