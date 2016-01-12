package com.bj.test.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作类
 * 其他DAO继承此类获取常用的数据库操作方法
 *
 * @param <T> 模型
 */
public interface BaseDaoI<T> {

    void save(T o);

    void delete(T o);

    void update(T o);

    void saveOrUpdate(T o);

    T getById(Class<T> clazz, Serializable id);

    T getByHql(String hql);

    T getByHql(String hql, Map<String, Object> params);

    List<T> find(String hql);

    List<T> find(String hql, Map<String, Object> params);

    List<T> find(String hql, Object... args);

    List<T> find(String hql, int page, int rows);

    List<T> find(String hql, Map<String, Object> params, int page, int rows);

    int count(String hql);

    int count(String hql, Map<String, Object> params);

    int executeHql(String hql);

    int executeHql(String hql, Map<String, Object> params);

    //sql操作相关部分
    T getUniqueBySql(String sql, Map<String, Object> params);

    T getUniqueBySql(String sql, Object... args);

    //List<Map> findBySql(String sql);
    //List<Map> findBySql(String sql, int page, int rows);
    //List<Map> findBySql(String sql, Map<String, Object> params);
    //List<Map> findBySql(String sql, Object...args);
    //List<Map> findBySql(String sql, Map<String, Object> params, int page, int rows);

    <E> List<E> findBySql(String sql, Class<E> clazz);

    <E> List<E> findBySql(String sql, Class<E> clazz, int page, int rows);

    <E> List<E> findBySql(String sql, Class<E> clazz, Map<String, Object> params);

    <E> List<E> findBySql(String sql, Class<E> clazz, Object... args);

    <E> List<E> findBySql(String sql, Class<E> clazz, Map<String, Object> params, int page, int rows);

    int executeSql(String sql);

    int executeSql(String sql, Map<String, Object> params);

    int countBySql(String sql);

    int countBySql(String sql, Map<String, Object> params);

}
