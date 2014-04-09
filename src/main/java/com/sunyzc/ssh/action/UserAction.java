package com.sunyzc.ssh.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.entity.User;
import com.sunyzc.ssh.service.RoleService;
import com.sunyzc.ssh.service.UserService;
import com.sunyzc.ssh.util.HQLHelper;

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

	/** input页面显示所有角色列表. */
	public List<Role> getAllRoleList() {
		return roleService.findAll();
	}

	/** input页面显示用户拥有的角色. */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
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
