package com.sunyzc.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.sunyzc.ssh.dao.ResourceDao;
import com.sunyzc.ssh.entity.Resource;

public class ResourceDaoImpl extends BaseDaoImpl<Resource, Long> implements ResourceDao {

	@Override
	public Resource getResourceByResourceName(String resourceName) {
		String hql = "FROM Resource resource WHERE resource.resourceName = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, resourceName);
		return (Resource) query.uniqueResult();
	}

	@Override
	public boolean isResourceNameUnique(String resourceName, String oldResourceName) {
		if (resourceName == null || resourceName.equals(oldResourceName))
			return true;
		else
			return getResourceByResourceName(resourceName) == null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllActionPath() {
		String hql = "SELECT DISTINCT resource.actionPath FROM Resource resource WHERE resource.actionPath IS NOT NULL";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}