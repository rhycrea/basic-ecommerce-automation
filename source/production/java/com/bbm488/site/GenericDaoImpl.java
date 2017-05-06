package com.bbm488.site;

/**
 * Created by Aybars on 9.04.2017.
 */

import com.bbm488.site.owner.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public abstract class GenericDaoImpl<E> implements GenericDao<E> {



    public final Class<E> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public E findById(final Serializable id) {
        return (E) getSession().get(this.type, id);
    }

    @Override
    public Serializable save(E entity) {
        return getSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(E entity) {
        getSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<E> entities = findAll();
        for (E entity : entities) {
            getSession().delete(entity);
        }
    }

    @Override
    public List<E> findAll() {
        return getSession().createCriteria(this.type).list();
    }

    @Override
    public List<E> findAllByExample(E entity) {
        Example example = Example.create(entity).ignoreCase().enableLike().excludeZeroes();
        return getSession().createCriteria(this.type).add(example).list();
    }

    @Override
    public void clear() {
        getSession().clear();

    }

    @Override
    public void flush() {
        getSession().flush();

    }

    public String getOwnerUname() {
        return Owner.getUname();
    }

    public String getOwnerUpass() {
        return Owner.getUpass();
    }

}