package com.bj.test.service;

import com.bj.test.util.HqlFilter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 基础业务逻辑类，其他service继承此service获得基本的业务
 *
 * @param <T>
 * @author 孙宇
 */
public interface BaseServiceI<T> {

    void save(T o);

    void delete(T o);

    void update(T o);

    void saveOrUpdate(T o);

    T getById(Serializable id);

    T getByHql(String hql);

    T getByHql(String hql, Map<String, Object> params);

    /**
     * 通过HqlFilter获取一个对象
     *
     * @param hqlFilter
     * @return
     */
    T getByFilter(HqlFilter hqlFilter);

    List<T> find();

    List<T> find(int page, int rows);

    List<T> find(String hql);

    List<T> find(String hql, Map<String, Object> params);

    List<T> find(String hql, int page, int rows);

    List<T> find(String hql, Map<String, Object> params, int page, int rows);

    List<T> findByFilter(HqlFilter hqlFilter);

    List<T> findByFilter(HqlFilter hqlFilter, int page, int rows);

    int count();

    int countByFilter(HqlFilter hqlFilter);

    int count(String hql);

    int count(String hql, Map<String, Object> params);

}
