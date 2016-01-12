package com.bj.test.dao.impl;

import com.bj.test.dao.BaseDaoI;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by baojun on 2016/1/12.
 */
@Repository
public class BaseDaoImpl<T> implements BaseDaoI<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T o) {
        saveOrUpdate(o);
    }

    @Override
    public void delete(T o) {
        if (o != null) {
            getCurrentSession().delete(o);
        }
    }

    @Override
    public void update(T o) {
        if (o != null) {
            getCurrentSession().update(o);
        }
    }

    @Override
    public void saveOrUpdate(T o) {
        if (o != null) {
            getCurrentSession().saveOrUpdate(o);
        }
    }

    @Override
    public T getById(Class<T> clazz, Serializable id) {
        return getCurrentSession().get(clazz, id);
    }

    @Override
    public T getByHql(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public T getByHql(String hql, Map<String, Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public List<T> find(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return q.list();
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql, Object... args) {
        Query q = getCurrentSession().createQuery(hql);
        for (int i = 0; i < args.length; i++) {
            q.setParameter(i, args[i]);
        }
        return q.list();
    }

    @Override
    public List<T> find(String hql, int page, int rows) {
        return find(hql, null, page, rows);
    }

    @Override
    public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public int count(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return ((Long) q.uniqueResult()).intValue();
    }

    @Override
    public int count(String hql, Map<String, Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return ((Long) q.uniqueResult()).intValue();
    }

    @Override
    public int executeHql(String hql) {
        Query q = getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    @Override
    public int executeHql(String hql, Map<String, Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    public T getUniqueBySql(String sql, Map<String, Object> params) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (T) q.setResultTransformer(Transformers.aliasToBean(clazz)).uniqueResult();
    }

    @Override
    public T getUniqueBySql(String sql, Object... args) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        for (int i = 0; i < args.length; i++) {
            q.setParameter(i, args[i]);
        }
        return (T) q.setResultTransformer(Transformers.aliasToBean(clazz)).uniqueResult();
    }

    @Override
    public <E> List<E> findBySql(String sql, Class<E> clazz) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return q.setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    @Override
    public <E> List<E> findBySql(String sql, Class<E> clazz, int page, int rows) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    @Override
    public <E> List<E> findBySql(String sql, Class<E> clazz, Map<String, Object> params) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    @Override
    public <E> List<E> findBySql(String sql, Class<E> clazz, Object... args) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        for (int i = 0; i < args.length; i++) {
            q.setParameter(i, args[i]);
        }
        return q.setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    @Override
    public <E> List<E> findBySql(String sql, Class<E> clazz, Map<String, Object> params, int page, int rows) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }

    @Override
    public int executeSql(String sql) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    public int countBySql(String sql) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        return ((Long) q.uniqueResult()).intValue();
    }

    @Override
    public int countBySql(String sql, Map<String, Object> params) {
        SQLQuery q = getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return ((Long) q.uniqueResult()).intValue();
    }
}
