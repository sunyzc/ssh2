package com.sunyzc.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import com.sunyzc.ssh.dao.RoleDao;
import com.sunyzc.ssh.entity.Page;
import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.service.RoleService;
import com.sunyzc.ssh.util.HQLHelper;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;

	@Override
	public Role load(Long id) {
		return roleDao.load(id);
	}

	@Override
	public Role get(Long id) {
		return roleDao.get(id);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public List<Role> findAll(Long[] ids) {
		return roleDao.findAll(ids);
	}

	@Override
	public List<Role> findAll(Collection<Long> ids) {
		return roleDao.findAll(ids);
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}

	@Override
	public Page<Role> getPage(Page<Role> page, HQLHelper<Role> helper) {
		return roleDao.getPage(page, helper);
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object value, Object oldValue) {
		return roleDao.isPropertyUnique(propertyName, value, oldValue);
	}

	@Override
	public Role getUniqueBy(String propertyName, Object value) {
		return roleDao.getUniqueBy(propertyName, value);
	}

	@Override
	public List<Role> findListBy(String propertyName, Object value) {
		return roleDao.findListBy(propertyName, value);
	}

	@Override
	public Role getRoleByRoleName(String roleName) {
		return getUniqueBy("roleName", roleName);
	}

	@Override
	public boolean isRoleNameUnique(String roleName, String odRoleName) {
		return isPropertyUnique("roleName", roleName, odRoleName);
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}
