package com.sunyzc.ssh.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/** 用于辅助生成HQL与参数列表 */
public class HQLHelper<T> {
	private String fromClause; // From子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句
	private List<Object> parameters = new ArrayList<Object>();

	/** 生成From子句，默认别名为"o" */
	public HQLHelper(Class<T> clazz) {
		this.fromClause = "FROM " + clazz.getSimpleName() + " o";
	}

	/** 生成From子句，使用指定的别名 */
	public HQLHelper(Class<T> clazz, String alias) {
		this.fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/** 拼接Where子句 @param condition 表达式 @param params */
	public HQLHelper<T> addWhereCondition(String condition, Object... params) {
		if (whereClause.length() == 0)
			whereClause = " WHERE " + condition;
		else
			whereClause += " AND " + condition;
		if (params != null && params.length > 0)
			for (Object param : params)
				this.parameters.add(param);
		return this;
	}

	/** 拼接OrderBy子句 */
	public HQLHelper<T> addOrderByProperty(String propertyName, boolean isAsc) {
		if (orderByClause.length() == 0)
			orderByClause = " ORDER BY " + propertyName + (isAsc ? " ASC" : " DESC");
		else
			orderByClause += ", " + propertyName + (isAsc ? " ASC" : " DESC");
		return this;
	}

	/** 获取查询数据列表的HQL语句 */
	public String getListHQL() {
		return fromClause + whereClause + orderByClause;
	}

	/** 获取查询数据列表的Query对象 */
	public Query getListQuery(Session session) {
		Query query = session.createQuery(getListHQL());
		for (int i = 0; i < parameters.size(); i++)
			query.setParameter(i, parameters.get(i));
		return query;
	}

	/** 获取查询总记录数的HQL语句（没有OrderBy子句） */
	public String getCountHQL() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/** 获取查询总记录数的Query对象（没有OrderBy子句） */
	public Query getCountQuery(Session session) {
		Query query = session.createQuery(getCountHQL());
		for (int i = 0; i < parameters.size(); i++)
			query.setParameter(i, parameters.get(i));
		return query;
	}

	/** 获取参数值列表 */
	public List<Object> getParameters() {
		return parameters;
	}
}
