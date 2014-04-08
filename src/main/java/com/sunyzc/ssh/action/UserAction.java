package com.sunyzc.ssh.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Resource;
import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.entity.User;
import com.sunyzc.ssh.service.RoleService;
import com.sunyzc.ssh.service.UserService;
import com.sunyzc.ssh.util.HQLHelper;
import com.sunyzc.ssh.util.SysConstants;

public class UserAction extends BaseAction<User> {
	private static final long serialVersionUID = 8683878162525847072L;
	private UserService userService;
	private RoleService roleService;
	private List<Long> checkedRoleIds;// = new ArrayList<Long>();

	@Override
	public String list() throws Exception {
		HQLHelper<User> helper = new HQLHelper<User>(User.class);
		page = userService.getPage(page, helper);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		Iterator<Role> iterator = model.getRoles().iterator();
		if (checkedRoleIds == null)
			checkedRoleIds = new ArrayList<Long>();
		while (iterator.hasNext())
			checkedRoleIds.add(iterator.next().getId());
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if (checkedRoleIds != null)
			// TODO 此方式会先DELETE所有再INSERT选中（即使没有修改），需要优化
			model.setRoles(new HashSet<Role>(roleService.findAll(checkedRoleIds)));
		userService.save(model);
		return TO_LIST;
	}

	@Override
	public String delete() throws Exception {
		userService.delete(id);
		return TO_LIST;
	}

	public String loginUI() throws Exception {
		return LOGIN;
	}

	public String login() throws Exception {
		User user = userService.getUserByLoginName(model.getLoginName());
		if (user == null) {
			addActionError("Login name does not exist");
			return LOGIN;
		} else if (model.getPassword() == null || !model.getPassword().equals(user.getPassword())) {
			addActionError("Password is incorrect");
			return LOGIN;
		}
		Set<String> allGrantedActionPaths = new HashSet<String>();
		for (Role role : user.getRoles())
			for (Authority authority : role.getAuthorities())
				for (Resource resource : authority.getResources())
					if (resource.getActionPath() != null)
						allGrantedActionPaths.add(resource.getActionPath());
		ActionContext.getContext().getSession().put(SysConstants.USER_IN_SESSION, user);
		ActionContext.getContext().getSession().put(SysConstants.ALL_AUTHORISED_ACTION_PATHS, allGrantedActionPaths);
		return HOME;
	}

	public String logout() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

	/**
	 * input页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return roleService.findAll();
	}

	/**
	 * input页面显示用户拥有的角色.
	 */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	/**
	 * input页面提交用户拥有的角色.
	 */
	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}

	public void prepareLogin() throws Exception {
		prepareModel();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null)
			model = new User();
		else
			model = userService.get(id);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
