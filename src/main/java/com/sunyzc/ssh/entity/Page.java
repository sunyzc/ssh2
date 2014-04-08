package com.sunyzc.ssh.entity;

import java.util.Collections;
import java.util.List;

/** 与具体ORM实现无关的分页参数及查询结果封装 */
public class Page<T> {
	protected int pageNo = 1;// 当前页码，默认为1
	protected int pageSize = 10;// 每页显示的数量，默认10条
	private int pageCount; // 总页数
	protected long totalCount;// 总记录数
	private int beginPageNo; // 页码列表的开始索引
	private int endPageNo; // 页码列表的结束索引
	protected List<T> recordList = Collections.emptyList();// 结果记录列表

	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	/** 计算beginPageNo与endPageNo */
	public Page<T> calculateBeginAndEndNo() {
		// 计算pageCount
		pageCount = getPageCount();
		if (pageCount <= 10) {
			// 总页数小于等于10页，则全部显示
			beginPageNo = 1;
			endPageNo = pageCount;
		} else {
			// 显示当前页附近的共10个页码（前4页 + 当前页 + 后5页）
			beginPageNo = pageNo - 4; // 3
			endPageNo = pageNo + 5; // 12
			if (beginPageNo < 1) {
				// 前面不足4个页码，直接显示前10页
				beginPageNo = 1;
				endPageNo = 10;
			} else if (endPageNo > pageCount) {
				// 后面不足5个页码，直接显示后10页
				endPageNo = pageCount;
				beginPageNo = pageCount - 10 + 1; // 显示时是包含两个边界值的
			}
		}
		return this;
	}

	/** 获得当前页的页号,序号从1开始,默认为1. */
	public int getPageNo() {
		return pageNo;
	}

	/** 设置当前页的页号,序号从1开始,低于1时自动调整为1. */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1)
			this.pageNo = 1;
	}

	/** 获得每页的记录数量, 默认为-1. */
	public int getPageSize() {
		return pageSize;
	}

	/** 设置每页的记录数量. */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	/** 根据pageSize与totalCount计算总页数. */
	public int getPageCount() {
		return (int) ((totalCount + pageSize - 1) / pageSize);
	}

	/** 获得总记录数, 默认值为-1. */
	public long getTotalCount() {
		return totalCount;
	}

	/** 设置总记录数. */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	public int getBeginPageNo() {
		return beginPageNo;
	}

	public void setBeginPageNo(int beginPageNo) {
		this.beginPageNo = beginPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	/** 获得页内的记录列表. */
	public List<T> getRecordList() {
		return recordList;
	}

	/** 设置页内的记录列表. */
	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	/** 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始. */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/** 是否还有上一页. */
	public boolean hasPrev() {
		return pageNo > 1;
	}

	/** 是否有下一页. */
	public boolean hasNext() {
		return (pageNo < getPageCount());
	}

	/** 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号. */
	public int getPrePage() {
		if (hasPrev())
			return pageNo - 1;
		else
			return pageNo;
	}

	/** 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号. */
	public int getNextPage() {
		if (hasNext())
			return pageNo + 1;
		else
			return pageNo;
	}
}
