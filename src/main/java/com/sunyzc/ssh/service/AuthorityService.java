package com.sunyzc.ssh.service;

import java.util.List;

import com.sunyzc.ssh.dao.BaseDao;
import com.sunyzc.ssh.entity.Authority;

public interface AuthorityService extends BaseDao<Authority, Long> {
	public Authority getAuthorityByAuthorityName(String authorityName);

	public boolean isAuthorityNameUnique(String authorityName, String oldAuthorityName);

	public List<Authority> findTopAuthorities();
}
