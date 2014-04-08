package com.sunyzc.ssh.dao.impl;

import org.hibernate.Query;

import com.sunyzc.ssh.dao.RoleDao;
import com.sunyzc.ssh.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {
	public Role getRoleByRoleName(String roleName) {
		String hql = "FROM Role role WHERE role.roleName = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, roleName);
		return (Role) query.uniqueResult();
	}

	public boolean isRoleNameUnique(String roleName, String oldRoleName) {
		if (roleName == null || roleName.equals(oldRoleName))
			return true;
		else
			return getRoleByRoleName(roleName) == null;
	}
}