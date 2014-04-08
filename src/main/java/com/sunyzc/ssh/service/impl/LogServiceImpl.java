package com.sunyzc.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import com.sunyzc.ssh.dao.LogDao;
import com.sunyzc.ssh.entity.Log;
import com.sunyzc.ssh.entity.Page;
import com.sunyzc.ssh.service.LogService;
import com.sunyzc.ssh.util.HQLHelper;

public class LogServiceImpl implements LogService {
	private LogDao logDao;

	@Override
	public Log load(Long id) {
		return logDao.load(id);
	}

	@Override
	public Log get(Long id) {
		return logDao.get(id);
	}

	@Override
	public List<Log> findAll() {
		return logDao.findAll();
	}

	@Override
	public List<Log> findAll(Long[] ids) {
		return logDao.findAll(ids);
	}

	@Override
	public List<Log> findAll(Collection<Long> ids) {
		return logDao.findAll(ids);
	}

	@Override
	public void save(Log log) {
		logDao.save(log);
	}

	@Override
	public void delete(Log log) {
		logDao.delete(log);
	}

	@Override
	public void delete(Long id) {
		logDao.delete(id);
	}

	@Override
	public Page<Log> getPage(Page<Log> page, HQLHelper<Log> helper) {
		return logDao.getPage(page, helper);
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object value, Object oldValue) {
		return logDao.isPropertyUnique(propertyName, value, oldValue);
	}

	@Override
	public Log getUniqueBy(String propertyName, Object value) {
		return logDao.getUniqueBy(propertyName, value);
	}

	@Override
	public List<Log> findListBy(String propertyName, Object value) {
		return logDao.findListBy(propertyName, value);
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
}
