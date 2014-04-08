package com.sunyzc.ssh.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRequestListener implements ServletRequestListener {
	private Logger log = LoggerFactory.getLogger(getClass());

	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		request.setAttribute("requestStartTime", System.currentTimeMillis());
		log.info("IP " + request.getRemoteAddr() + " 访问了 " + request.getRequestURI());
	}

	public void requestDestroyed(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		long requestStartTime = (Long) request.getAttribute("requestStartTime");
		long durationTime = System.currentTimeMillis() - requestStartTime;
		log.info(request.getRequestURI() + " 请求处理结束，用时 " + durationTime + " 毫秒");
	}
}