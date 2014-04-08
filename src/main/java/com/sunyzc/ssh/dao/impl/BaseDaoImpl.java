package com.sunyzc.ssh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;

import com.sunyzc.ssh.dao.BaseDao;
import com.sunyzc.ssh.entity.Page;
import com.sunyzc.ssh.util.HQLHelper;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	private SessionFactory sessionFactory;
	protected Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public T load(final PK id) {
		return (T) getSession().load(clazz, id);
	}

	@Override
	public T get(final PK id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		// return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
		return getSession().createCriteria(clazz).list();
	}

	@Override
	public List<T> findAll(final PK[] ids) {
		if (ids == null || ids.length == 0)
			return Collections.emptyList();
		// FROM User WHERE id IN (?, ?, ? ...)
		return createCriteria(Restrictions.in(getIdName(), ids)).list();
	}

	@Override
	public List<T> findAll(final Collection<PK> ids) {
		if (ids == null || ids.size() == 0)
			return Collections.emptyList();
		// FROM User WHERE id IN (?, ?, ? ...)
		return createCriteria(Restrictions.in(getIdName(), ids)).list();
	}

	@Override
	public void save(final T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(final T entity) {
		getSession().delete(entity);
	}

	@Override
	public void delete(final PK id) {
		getSession().delete(get(id));
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(oldValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object value, final Object oldValue) {
		if (value == null || value.equals(oldValue))
			return true;
		return (getUniqueBy(propertyName, value) == null);
	}

	/** 按属性查找唯一对象, value为空时匹配方式为IS NULL, 不为空时匹配方式为相等. */
	public T getUniqueBy(final String propertyName, final Object value) {
		if (value == null)
			return (T) createCriteria(Restrictions.isNull(propertyName)).uniqueResult();
		else
			return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/** 按属性查找对象列表, value为空时匹配方式为IS NULL, 不为空时匹配方式为相等. */
	public List<T> findListBy(final String propertyName, final Object value) {
		if (value == null)
			return (List<T>) createCriteria(Restrictions.isNull(propertyName)).list();
		else
			return (List<T>) createCriteria(Restrictions.eq(propertyName, value)).list();
	}

	/** 公共查询分页信息的方法 */
	public Page<T> getPage(Page<T> page, HQLHelper<T> helper) {
		Session session = getSession();
		page.setTotalCount((Long) helper.getCountQuery(session).uniqueResult());
		page.setRecordList(helper.getListQuery(session).setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).list());
		return page.calculateBeginAndEndNo();
	}

	/** 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作. */
	private Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(clazz);
		for (Criterion c : criterions)
			criteria.add(c);
		return criteria;
	}

	/**
	 * 取得对象的主键名.
	 */
	private String getIdName() {
		ClassMetadata meta = sessionFactory.getClassMetadata(clazz);
		return meta.getIdentifierPropertyName();
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
