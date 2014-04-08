package com.sunyzc.ssh.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sunyzc.ssh.dao.AuthorityDao;
import com.sunyzc.ssh.dao.RoleDao;
import com.sunyzc.ssh.dao.UserDao;
import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.entity.User;

public class AccountServiceTest {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserDao userDao = (UserDao) context.getBean("userDao");
	private RoleDao roleDao = (RoleDao) context.getBean("roleDao");
	private AuthorityDao authorityDao = (AuthorityDao) context.getBean("authorityDao");

	@Test
	public void testGetUser() {
		try {
			User user = userDao.get(1L);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(user.getUserName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUserUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUserLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRole() {
		Role role = roleDao.get(1L);
		System.out.println(role.getRoleName());
	}

	@Test
	public void testSaveRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRoleRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRoleLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuthority() {
		Authority authority = authorityDao.get(1L);
		System.out.println(authority.getAuthorityName());
	}

	@Test
	public void testSaveAuthority() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAuthorityAuthority() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAuthorityLong() {
		fail("Not yet implemented");
	}
}
