package com.sunyzc.ssh.interceptor;

import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sunyzc.ssh.util.SysConstants;

public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -491418985442121551L;

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		List<String> allActionPaths = (List<String>) ActionContext.getContext().getApplication().get(SysConstants.ALL_ACTION_PATHS);
		String currentActionPath = invocation.getProxy().getNamespace() + invocation.getProxy().getActionName() + "!" + invocation.getProxy().getMethod();
		if (!allActionPaths.contains(currentActionPath))
			return invocation.invoke();
		else {
			Set<String> allGrantedActionPaths = (Set<String>) ActionContext.getContext().getSession().get(SysConstants.ALL_AUTHORISED_ACTION_PATHS);
			if (allGrantedActionPaths != null && allGrantedActionPaths.contains(currentActionPath))
				return invocation.invoke();
			else
				return "login";
		}
	}
}
