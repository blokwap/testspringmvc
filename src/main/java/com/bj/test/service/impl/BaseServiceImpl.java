package com.bj.test.service.impl;

import com.bj.test.dao.BaseDaoI;
import com.bj.test.model.BasePojo;
import com.bj.test.model.User;
import com.bj.test.service.BaseServiceI;
import com.bj.test.util.HqlFilter;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by baojun on 2016/1/12.
 */
@Service
@Transactional
public class BaseServiceImpl<T extends BasePojo> implements BaseServiceI<T> {
    @Autowired
    private BaseDaoI<T> baseDao;

    @Override
    public void save(T o) {
        o.setCreateUser((User) SecurityUtils.getSubject().getPrincipal());
        o.setCreateTime(new Date());
        saveOrUpdate(o);
    }

    @Override
    public void delete(T o) {
        baseDao.delete(o);
    }

    @Override
    public void update(T o) {
        o.setUpdateUser((User) SecurityUtils.getSubject().getPrincipal());
        o.setUpdateTime(new Date());
        baseDao.update(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        baseDao.saveOrUpdate(o);
    }

    @Override
    public T getById(Serializable id) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return baseDao.getById(clazz, id);
    }

    @Override
    public T getByHql(String hql) {
        return getByHql(hql, null);
    }

    @Override
    public T getByHql(String hql, Map<String, Object> params) {
        return baseDao.getByHql(hql, params);
    }

    @Override
    public T getByFilter(HqlFilter hqlFilter) {
        String className = ((Class<BasePojo>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
        String hql = "select distinct t from " + className + " t";
        return getByHql(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
    }

    @Override
    public List<T> find() {
        return findByFilter(new HqlFilter());
    }

    @Override
    public List<T> find(int page, int rows) {
        return findByFilter(new HqlFilter(), page, rows);
    }

    @Override
    public List<T> find(String hql) {
        return findByFilter(new HqlFilter());
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        return baseDao.find(hql, params);
    }

    @Override
    public List<T> find(String hql, int page, int rows) {
        return baseDao.find(hql, page, rows);
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
        return baseDao.find(hql, params, page, rows);
    }

    @Override
    public List<T> findByFilter(HqlFilter hqlFilter) {
        String className = ((Class<BasePojo>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
        String hql = "select distinct t from " + className + " t";
        return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
    }

    @Override
    public List<T> findByFilter(HqlFilter hqlFilter, int page, int rows) {
        String className = ((Class<BasePojo>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
        String hql = "select distinct t from " + className + " t";
        return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams(), page, rows);
    }

    @Override
    public int count() {
        return countByFilter(new HqlFilter());
    }

    @Override
    public int countByFilter(HqlFilter hqlFilter) {
        String className = ((Class<BasePojo>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
        String hql = "select count(distinct t) from " + className + " t";
        return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
    }

    @Override
    public int count(String hql) {
        return baseDao.count(hql);
    }

    @Override
    public int count(String hql, Map<String, Object> params) {
        return baseDao.count(hql, params);
    }
}
