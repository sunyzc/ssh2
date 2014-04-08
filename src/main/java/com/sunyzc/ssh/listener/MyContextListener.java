package com.sunyzc.ssh.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Resource;
import com.sunyzc.ssh.service.AuthorityService;
import com.sunyzc.ssh.service.ResourceService;
import com.sunyzc.ssh.util.SysConstants;

public class MyContextListener implements ServletContextListener {
	private Logger log = LoggerFactory.getLogger(getClass());

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ResourceService resourceService = (ResourceService) applicationContext.getBean("resourceService");
		AuthorityService authorityService = (AuthorityService) applicationContext.getBean("authorityService");

		// 准备所有资源表中的actionPath到application作用域中
		List<String> allActionPaths = resourceService.findAllActionPath();
		servletContext.setAttribute(SysConstants.ALL_ACTION_PATHS, allActionPaths);

		// 准备所有资源map到application作用域中
		Map<String, Resource> allResourceMap = new HashMap<String, Resource>();
		List<Resource> allResources = resourceService.findAll();
		for (Resource resource : allResources)
			allResourceMap.put(resource.getActionPath(), resource);
		servletContext.setAttribute(SysConstants.ALL_RESOURCE_MAP, allResourceMap);

		// 准备所有顶级权限的数据到application作用域中
		List<Authority> allTopAuthorities = authorityService.findTopAuthorities();
		servletContext.setAttribute(SysConstants.ALL_TOP_AUTHORITIES, allTopAuthorities);

		System.out.println("-- 初始化时的数据准备工作完毕 --");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		log.info("正在关闭 " + servletContext.getContextPath());
	}
}