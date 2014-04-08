package com.sunyzc.ssh.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.sunyzc.ssh.entity.Page;
import com.sunyzc.ssh.util.HQLHelper;

public interface BaseDao<T, PK extends Serializable> {
	public T load(final PK id);

	public T get(final PK id);

	public List<T> findAll();

	public List<T> findAll(final PK[] ids);

	public List<T> findAll(final Collection<PK> ids);

	public void save(final T entity);

	public void delete(final T entity);

	public void delete(final PK id);

	public Page<T> getPage(Page<T> page, HQLHelper<T> helper);

	public boolean isPropertyUnique(final String propertyName, final Object value, final Object oldValue);

	public T getUniqueBy(final String propertyName, final Object value);

	public List<T> findListBy(final String propertyName, final Object value);
}