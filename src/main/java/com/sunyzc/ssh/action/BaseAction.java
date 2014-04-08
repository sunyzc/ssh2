package com.sunyzc.ssh.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.sunyzc.ssh.entity.Page;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>, Preparable {
	private static final long serialVersionUID = -1653204626115064950L;
	protected Logger log = LoggerFactory.getLogger(getClass());
	/** 进行增删改操作后,以redirect方式重新打开action默认页的result名. */
	protected static final String HOME = "home";
	protected static final String TO_LIST = "toList";
	protected Page<T> page = new Page<T>();
	protected Long id;
	protected T model;

	@Override
	public T getModel() {
		return model;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<T> getPage() {
		return page;
	}

	/** Action函数, 默认的action函数, 默认调用list()函数. */
	@Override
	public String execute() throws Exception {
		return list();
	}

	// ---- CRUD Action函数 ----//
	/** Action函数,显示Entity列表界面. 一般return SUCCESS. */
	public abstract String list() throws Exception;

	/** Action函数,显示新增或修改Entity界面. 一般return INPUT. */
	@Override
	public abstract String input() throws Exception;

	/** Action函数,新增或修改Entity. 建议return TO_LIST. */
	public abstract String save() throws Exception;

	/** Action函数,删除Entity. 建议return TO_LIST. */
	public abstract String delete() throws Exception;

	/** 实现空的prepare()函数,屏蔽了所有Action函数都会执行的公共的二次绑定. */
	public void prepare() throws Exception {
	}

	public void prepareInput() throws Exception {
		prepareModel();
	}

	public void prepareSave() throws Exception {
		prepareModel();
	}

	/** 等同于prepare()的内部函数,供prepardMethodName()函数调用. */
	protected abstract void prepareModel() throws Exception;

}
