package com.sunyzc.ssh.dao.impl;

import org.hibernate.Query;

import com.sunyzc.ssh.dao.UserDao;
import com.sunyzc.ssh.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {
	public User getUserByLoginName(String loginName) {
		String hql = "FROM User user WHERE user.loginName = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, loginName);
		return (User) query.uniqueResult();
	}

	public boolean isLoginNameUnique(String loginName, String oldLoginName) {
		if (loginName == null || loginName.equals(oldLoginName))
			return true;
		else
			return getUserByLoginName(loginName) == null;
	}
}