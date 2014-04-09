package com.sunyzc.ssh.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Resource;
import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.entity.User;

public class ServiceTest {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserService userService = (UserService) context.getBean("userService");
	private RoleService roleService = (RoleService) context.getBean("roleService");
	private AuthorityService authorityService = (AuthorityService) context.getBean("authorityService");
	private ResourceService resourceService = (ResourceService) context.getBean("resourceService");

	@Test
	public void testGetUser() {
		User user = userService.get(1L);
		System.out.println(user.getUserName());
	}

	@Test
	public void testGetRole() {
		Role role = roleService.get(1L);
		System.out.println(role.getRoleName());
	}

	@Test
	public void testGetAuthority() {
		Authority authority = authorityService.get(1L);
		System.out.println(authority.getAuthorityName());
	}

	@Test
	public void testGetResource() {
		Resource resource = resourceService.get(1L);
		System.out.println(resource.getResourceName());
	}
}
