package com.sunyzc.ssh.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Resource;
import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.entity.User;

public class DataInitialService {
	private SessionFactory sessionFactory;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataInitialService dataInitialService = (DataInitialService) applicationContext.getBean("dataInitialService");
		dataInitialService.save();
		applicationContext.close();
	}

	public void save() {
		Session session = sessionFactory.getCurrentSession();
		// Add Resources
		Resource user_execute = new Resource("/user!execute", null);
		Resource user_list = new Resource("/user!list", null);
		Resource user_input = new Resource("/user!input", null);
		Resource user_save = new Resource("/user!save", null);
		Resource user_delete = new Resource("/user!delete", null);
		Resource role_execute = new Resource("/role!execute", null);
		Resource role_list = new Resource("/role!list", null);
		Resource role_input = new Resource("/role!input", null);
		Resource role_save = new Resource("/role!save", null);
		Resource role_delete = new Resource("/role!delete", null);
		Resource log_execute = new Resource("/log!execute", null);
		Resource log_list = new Resource("/log!list", null);
		Resource log_delete = new Resource("/log!delete", null);
		session.save(user_execute);
		session.save(user_list);
		session.save(user_input);
		session.save(user_save);
		session.save(user_delete);
		session.save(role_execute);
		session.save(role_list);
		session.save(role_input);
		session.save(role_save);
		session.save(role_delete);
		session.save(log_execute);
		session.save(log_list);
		session.save(log_delete);

		// Add authorities
		Authority authority_SystemManage = new Authority("系统管理", null);
		Authority authority_UserManage = new Authority("用户管理", authority_SystemManage);
		Authority authority_RoleManage = new Authority("角色管理", authority_SystemManage);
		Authority authority_LogManage = new Authority("日志管理", authority_SystemManage);
		Authority authority_UserList = new Authority("用户列表", authority_UserManage);
		authority_UserList.getResources().add(user_execute);
		authority_UserList.getResources().add(user_list);
		Authority authority_UserAdd = new Authority("用户添加和修改", authority_UserManage);
		authority_UserAdd.getResources().add(user_input);
		authority_UserAdd.getResources().add(user_save);
		Authority authority_UserDelete = new Authority("用户删除", authority_UserManage);
		authority_UserDelete.getResources().add(user_delete);
		Authority authority_RoleList = new Authority("角色列表", authority_RoleManage);
		authority_RoleList.getResources().add(role_execute);
		authority_RoleList.getResources().add(role_list);
		Authority authority_RoleAdd = new Authority("角色添加和修改", authority_RoleManage);
		authority_RoleAdd.getResources().add(role_input);
		authority_RoleAdd.getResources().add(role_save);
		Authority authority_RoleDelete = new Authority("角色删除", authority_RoleManage);
		authority_RoleDelete.getResources().add(role_delete);
		Authority authority_LogList = new Authority("日志列表", authority_LogManage);
		authority_LogList.getResources().add(log_execute);
		authority_LogList.getResources().add(log_list);
		Authority authority_LogDelete = new Authority("日志删除", authority_LogManage);
		authority_LogDelete.getResources().add(log_delete);
		session.save(authority_SystemManage);
		session.save(authority_UserManage);
		session.save(authority_RoleManage);
		session.save(authority_LogManage);
		session.save(authority_UserList);
		session.save(authority_UserAdd);
		session.save(authority_UserDelete);
		session.save(authority_RoleList);
		session.save(authority_RoleAdd);
		session.save(authority_RoleDelete);
		session.save(authority_LogList);
		session.save(authority_LogDelete);

		// Add roles
		Role role_Admin = new Role("管理员");
		role_Admin.getAuthorities().add(authority_SystemManage);
		role_Admin.getAuthorities().add(authority_RoleManage);
		role_Admin.getAuthorities().add(authority_RoleList);
		role_Admin.getAuthorities().add(authority_RoleAdd);
		role_Admin.getAuthorities().add(authority_RoleDelete);
		session.save(role_Admin);

		// Add users
		User user_Admin = new User("admin", "admin", "系统管理员", "admin@163.com", true, new Date(), new Date(), "初始化账户：系统管理员");
		user_Admin.getRoles().add(role_Admin);
		User user_User = new User("user", "user", "普通用户", "user@163.com", true, new Date(), new Date(), "初始化账户：普通用户");
		session.save(user_Admin);
		session.save(user_User);
	}

	public void delete() {
		Session session = sessionFactory.getCurrentSession();
		Role role = (Role) session.get(Role.class, 1L);
		// Resource resource = (Resource) session.get(Resource.class, 1L);
		session.delete(role);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
