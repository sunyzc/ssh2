package com.sunyzc.ssh.interceptor;

import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sunyzc.ssh.entity.Log;
import com.sunyzc.ssh.entity.Resource;
import com.sunyzc.ssh.entity.User;
import com.sunyzc.ssh.service.LogService;
import com.sunyzc.ssh.service.ResourceService;
import com.sunyzc.ssh.util.SysConstants;

public class LogInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 4252090539559131500L;
	// private Logger log = LoggerFactory.getLogger(getClass());
	private LogService logService;
	private ResourceService resourceService;

	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User) ActionContext.getContext().getSession().get(SysConstants.USER_IN_SESSION);
		String operation = null;
		String currentActionPath = invocation.getProxy().getNamespace() + invocation.getProxy().getActionName() + "!" + invocation.getProxy().getMethod();
		Resource resource = resourceService.getUniqueBy("actionPath", currentActionPath);
		if (resource != null)
			operation = resource.getResourceName();
		Log log = new Log(user, new Date(), operation);
		logService.save(log);
		return invocation.invoke();
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
}
