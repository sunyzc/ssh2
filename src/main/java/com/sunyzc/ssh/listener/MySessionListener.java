package com.sunyzc.ssh.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunyzc.ssh.util.SysConstants;

@SuppressWarnings("unchecked")
public class MySessionListener implements HttpSessionListener {
	private Logger log = LoggerFactory.getLogger(getClass());

	/** 事实上，存储sessions不可在此处，应在Auth之后 */
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		log.info("创建了一个新的session, id 为 " + session.getId());
		ServletContext servletContext = session.getServletContext();
		Set<HttpSession> sessions = (Set<HttpSession>) servletContext.getAttribute(SysConstants.ALL_SESSIONS);
		if (sessions == null) {
			sessions = new HashSet<HttpSession>();
			servletContext.setAttribute(SysConstants.ALL_SESSIONS, sessions);
		}
		sessions.add(session);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		log.info("销毁了一个session, id为 " + session.getId());
		ServletContext servletContext = session.getServletContext();
		HashSet<HttpSession> sessions = (HashSet<HttpSession>) servletContext.getAttribute(SysConstants.ALL_SESSIONS);
		sessions.remove(session);
	}
}