package com.sunyzc.ssh.service;

import com.sunyzc.ssh.dao.BaseDao;
import com.sunyzc.ssh.entity.User;

public interface UserService extends BaseDao<User, Long> {
	public User getUserByLoginName(String loginName);

	public boolean isLoginNameUnique(String loginName, String oldLoginName);
}
