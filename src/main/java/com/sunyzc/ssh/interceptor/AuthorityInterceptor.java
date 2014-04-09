package com.sunyzc.ssh.interceptor;

import java.util.Set;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sunyzc.ssh.util.SysConstants;

public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -491418985442121551L;

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		String currentActionPath = invocation.getProxy().getNamespace() + invocation.getProxy().getActionName() + "!" + invocation.getProxy().getMethod();
		// List<String> allActionPaths = (List<String>) ActionContext.getContext().getApplication().get(SysConstants.ALL_ACTION_PATHS);
		// if (!allActionPaths.contains(currentActionPath))
		// return invocation.invoke();
		// if (SysConstants.ACTION_PATH_LOGIN.equals(currentActionPath) || SysConstants.ACTION_PATH_LOGOUT.equals(currentActionPath))
		if (currentActionPath.startsWith(SysConstants.ACTION_LOGIN_PREFIX))
			return invocation.invoke();
		else {
			Set<String> allGrantedActionPaths = (Set<String>) ActionContext.getContext().getSession().get(SysConstants.ALL_AUTHORISED_ACTION_PATHS);
			if (allGrantedActionPaths != null && allGrantedActionPaths.contains(currentActionPath))
				return invocation.invoke();
			else
				return "login";
		}
	}

	@SuppressWarnings("unused")
	private boolean validateLoginInfoCookie() {
		Cookie cookie = getLoginInfoCookie();
		if (cookie != null) {
			String cookieValue = cookie.getValue();
			// TODO
			return true;
		}
		return false;
	}

	private Cookie getLoginInfoCookie() {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies != null && cookies.length > 0)
			for (Cookie cookie : cookies)
				if (SysConstants.COOKIE_FOR_LOGIN_INFO.equals(cookie.getName()))
					return cookie;
		return null;
	}
}
