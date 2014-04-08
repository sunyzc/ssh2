package com.sunyzc.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import com.sunyzc.ssh.dao.ResourceDao;
import com.sunyzc.ssh.entity.Page;
import com.sunyzc.ssh.entity.Resource;
import com.sunyzc.ssh.service.ResourceService;
import com.sunyzc.ssh.util.HQLHelper;

public class ResourceServiceImpl implements ResourceService {
	private ResourceDao resourceDao;

	@Override
	public Resource load(Long id) {
		return resourceDao.load(id);
	}

	@Override
	public Resource get(Long id) {
		return resourceDao.get(id);
	}

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public List<Resource> findAll(Long[] ids) {
		return resourceDao.findAll(ids);
	}

	@Override
	public List<Resource> findAll(Collection<Long> ids) {
		return resourceDao.findAll(ids);
	}

	@Override
	public void save(Resource resource) {
		resourceDao.save(resource);
	}

	@Override
	public void delete(Resource resource) {
		resourceDao.delete(resource);
	}

	@Override
	public void delete(Long id) {
		resourceDao.delete(id);
	}

	@Override
	public Page<Resource> getPage(Page<Resource> page, HQLHelper<Resource> helper) {
		return resourceDao.getPage(page, helper);
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object value, Object oldValue) {
		return resourceDao.isPropertyUnique(propertyName, value, oldValue);
	}

	@Override
	public Resource getUniqueBy(String propertyName, Object value) {
		return resourceDao.getUniqueBy(propertyName, value);
	}

	@Override
	public List<Resource> findListBy(String propertyName, Object value) {
		return resourceDao.findListBy(propertyName, value);
	}

	@Override
	public Resource getResourceByResourceName(String resourceName) {
		return getUniqueBy("resourceName", resourceName);
	}

	@Override
	public boolean isResourceNameUnique(String resourceName, String oldResourceName) {
		return isPropertyUnique("resourceName", resourceName, oldResourceName);
	}

	@Override
	public List<String> findAllActionPath() {
		return resourceDao.findAllActionPath();
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}
