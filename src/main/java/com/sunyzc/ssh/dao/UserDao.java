package com.sunyzc.ssh.dao;

import com.sunyzc.ssh.entity.User;

public interface UserDao extends BaseDao<User, Long> {

	public User getUserByLoginName(String loginName);

	public boolean isLoginNameUnique(String loginName, String oldLoginName);
}