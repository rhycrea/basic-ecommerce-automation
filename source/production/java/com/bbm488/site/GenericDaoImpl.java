package com.bbm488.site;

/**
 * Created by Aybars on 9.04.2017.
 */

import com.bbm488.site.customer.Customer;
import com.bbm488.site.owner.Owner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Map;


public abstract class GenericDaoImpl<K,T> implements GenericDao<K,T> {



    private Class<T> type;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public long countAll(final Map<String, Object> params) {
        return 12;
    }

    @Override
    public T create(final K k, final T t) {
        if(getDatabase(t).containsKey(k)) {
            return null;
        }
        getDatabase(t).put(k,t);
        return t;
    }

    @Override
    public void delete(final K k) {
    }

    @Override
    public T find(final K k) {
        return (T)new String();
    }

    @Override
    public T update(final K k, final T t) {

        getDatabase(t).put(k,t);
        return t;
    }

    @Override
    public Hashtable getDatabase(final T t) {
        if(t.getClass().isAssignableFrom(Customer.class)) {
            System.out.println("GenericDaoImpl: getDatabase: t.getClass(): " + t.getClass());
            return Database.getCustomerDB();
        }
        else if(t.getClass().isAssignableFrom(Order.class)) {
            System.out.println("GenericDaoImpl: getDatabase: t.getClass(): " + t.getClass());
            return Database.getOrderDB();
        }
        else if(t.getClass().isAssignableFrom(Product.class)) {
            System.out.println("GenericDaoImpl: getDatabase: t.getClass(): " + t.getClass());
            return Database.getProductDB();
        }
        return null;
    }

    public String getOwnerUname() {
        return Owner.getUname();
    }

    public String getOwnerUpass() {
        return Owner.getUpass();
    }

}