package com.sunyzc.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import com.sunyzc.ssh.dao.AuthorityDao;
import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Page;
import com.sunyzc.ssh.service.AuthorityService;
import com.sunyzc.ssh.util.HQLHelper;

public class AuthorityServiceImpl implements AuthorityService {
	private AuthorityDao authorityDao;

	@Override
	public Authority load(Long id) {
		return authorityDao.load(id);
	}

	@Override
	public Authority get(Long id) {
		return authorityDao.get(id);
	}

	@Override
	public List<Authority> findAll() {
		return authorityDao.findAll();
	}

	@Override
	public List<Authority> findAll(Long[] ids) {
		return authorityDao.findAll(ids);
	}

	@Override
	public List<Authority> findAll(Collection<Long> ids) {
		return authorityDao.findAll(ids);
	}

	@Override
	public void save(Authority authority) {
		authorityDao.save(authority);
	}

	@Override
	public void delete(Authority authority) {
		authorityDao.delete(authority);
	}

	@Override
	public void delete(Long id) {
		authorityDao.delete(id);
	}

	@Override
	public Page<Authority> getPage(Page<Authority> page, HQLHelper<Authority> helper) {
		return authorityDao.getPage(page, helper);
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object value, Object oldValue) {
		return authorityDao.isPropertyUnique(propertyName, value, oldValue);
	}

	@Override
	public Authority getUniqueBy(String propertyName, Object value) {
		return authorityDao.getUniqueBy(propertyName, value);
	}

	@Override
	public List<Authority> findListBy(String propertyName, Object value) {
		return authorityDao.findListBy(propertyName, value);
	}

	@Override
	public Authority getAuthorityByAuthorityName(String authorityName) {
		return getUniqueBy("authorityName", authorityName);
	}

	@Override
	public boolean isAuthorityNameUnique(String authorityName, String oldAuthorityName) {
		return isPropertyUnique("authorityName", authorityName, oldAuthorityName);
	}

	@Override
	public List<Authority> findTopAuthorities() {
		return findListBy("parent", null);
	}

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
}
