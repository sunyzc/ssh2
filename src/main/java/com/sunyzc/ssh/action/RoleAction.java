package com.sunyzc.ssh.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.sunyzc.ssh.entity.Authority;
import com.sunyzc.ssh.entity.Role;
import com.sunyzc.ssh.service.AuthorityService;
import com.sunyzc.ssh.service.RoleService;
import com.sunyzc.ssh.util.HQLHelper;

public class RoleAction extends BaseAction<Role> {
	private static final long serialVersionUID = 4948856371257589918L;
	private RoleService roleService;
	private AuthorityService authorityService;
	private List<Long> checkedAuthorityIds;// = new ArrayList<Long>();

	@Override
	public String list() throws Exception {
		HQLHelper<Role> helper = new HQLHelper<Role>(Role.class);
		page = roleService.getPage(page, helper);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		Iterator<Authority> iterator = model.getAuthorities().iterator();
		if (checkedAuthorityIds == null)
			checkedAuthorityIds = new ArrayList<Long>();
		while (iterator.hasNext())
			checkedAuthorityIds.add(iterator.next().getId());
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if (checkedAuthorityIds != null)
			model.setAuthorities(new HashSet<Authority>(authorityService.findAll(checkedAuthorityIds)));
		roleService.save(model);
		return TO_LIST;
	}

	@Override
	public String delete() throws Exception {
		roleService.delete(id);
		return TO_LIST;
	}

	/** input页面显示所有权限列表. */
	public List<Authority> getAllAuthorityList() {
		return authorityService.findAll();
	}

	/** input页面显示角色拥有的权限. */
	public List<Long> getCheckedAuthorityIds() {
		return checkedAuthorityIds;
	}

	public void setCheckedAuthorityIds(List<Long> checkedAuthorityIds) {
		this.checkedAuthorityIds = checkedAuthorityIds;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id == null)
			model = new Role();
		else
			model = roleService.get(id);
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}
}
