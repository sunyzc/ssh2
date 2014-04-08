package com.sunyzc.ssh.service;

import com.sunyzc.ssh.dao.BaseDao;
import com.sunyzc.ssh.entity.Role;

public interface RoleService extends BaseDao<Role, Long> {
	public Role getRoleByRoleName(String roleName);

	public boolean isRoleNameUnique(String roleName, String oldRoleName);
}
