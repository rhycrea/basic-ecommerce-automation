package com.bbm488.site;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Aybars on 7.04.2017.
 */
public interface GenericDao<K,T> {
    long countAll(Map<String, Object> params);

    T create(K k, T t);

    void delete(K k);

    T find(K k);

    T update(K k, T t);

    public Hashtable getDatabase(final T t);

    public String getOwnerUname();
    public String getOwnerUpass();
}