package com.sunyzc.ssh.dao;

import com.sunyzc.ssh.entity.Role;

public interface RoleDao extends BaseDao<Role, Long> {
	public Role getRoleByRoleName(String roleName);

	public boolean isRoleNameUnique(String roleName, String oldRoleName);
}