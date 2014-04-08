package com.sunyzc.ssh.dao;

import com.sunyzc.ssh.entity.Authority;

public interface AuthorityDao extends BaseDao<Authority, Long> {
	public Authority getAuthorityByAuthorityName(String authorityName);

	public boolean isAuthorityNameUnique(String authorityName, String oldAuthorityName);
}