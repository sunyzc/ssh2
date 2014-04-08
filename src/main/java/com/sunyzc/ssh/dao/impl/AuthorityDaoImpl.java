package com.sunyzc.ssh.dao.impl;

import org.hibernate.Query;

import com.sunyzc.ssh.dao.AuthorityDao;
import com.sunyzc.ssh.entity.Authority;

public class AuthorityDaoImpl extends BaseDaoImpl<Authority, Long> implements AuthorityDao {
	public Authority getAuthorityByAuthorityName(String authorityName) {
		String hql = "FROM Authority authority WHERE authority.authorityName = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, authorityName);
		return (Authority) query.uniqueResult();
	}

	public boolean isAuthorityNameUnique(String authorityName, String oldAuthorityName) {
		if (authorityName == null || authorityName.equals(oldAuthorityName))
			return true;
		else
			return getAuthorityByAuthorityName(authorityName) == null;
	}
}