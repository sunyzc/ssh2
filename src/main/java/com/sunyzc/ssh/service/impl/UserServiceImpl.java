package com.sunyzc.ssh.service.impl;

import java.util.Collection;
import java.util.List;

import com.sunyzc.ssh.dao.UserDao;
import com.sunyzc.ssh.entity.Page;
import com.sunyzc.ssh.entity.User;
import com.sunyzc.ssh.service.UserService;
import com.sunyzc.ssh.util.HQLHelper;

/** 本想serviceImpl也直接集成BaseDaoImpl，但事务不会开启，因为事务绑定在service层 */
public class UserServiceImpl /* extends BaseDaoImpl<User, Long> */implements UserService {
	private UserDao userDao;

	@Override
	public User load(Long id) {
		return userDao.load(id);
	}

	@Override
	public User get(Long id) {
		return userDao.get(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> findAll(Long[] ids) {
		return userDao.findAll(ids);
	}

	@Override
	public List<User> findAll(Collection<Long> ids) {
		return userDao.findAll(ids);
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public Page<User> getPage(Page<User> page, HQLHelper<User> helper) {
		return userDao.getPage(page, helper);
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object value, Object oldValue) {
		return userDao.isPropertyUnique(propertyName, value, oldValue);
	}

	@Override
	public User getUniqueBy(String propertyName, Object value) {
		return userDao.getUniqueBy(propertyName, value);
	}

	@Override
	public List<User> findListBy(String propertyName, Object value) {
		return userDao.findListBy(propertyName, value);
	}

	@Override
	public User getUserByLoginName(String loginName) {
		return getUniqueBy("loginName", loginName);
	}

	@Override
	public boolean isLoginNameUnique(String loginName, String oldLoginName) {
		return isPropertyUnique("loginName", loginName, oldLoginName);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
