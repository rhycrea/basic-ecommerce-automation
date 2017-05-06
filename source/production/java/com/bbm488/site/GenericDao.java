package com.bbm488.site;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Aybars on 7.04.2017.
 */
public interface GenericDao<E> {
    /**
     *
     * @param entity: entity to save
     * @return Identifier of saved entity
     */
    Serializable save(E entity);

    /**
     *
     * @param entity:entity to save or update
     */
    public void saveOrUpdate(E entity);

    /**
     *
     * @param entity: entity to delete
     */
    void delete( E entity );

    /**
     * Delete all records
     */
    void deleteAll();

    /**
     * Find all records
     * @return
     */
    List<E> findAll();

    /**
     * Find all records matching provided entity
     * @param entity: entity object used for search
     * @return
     */
    List<E> findAllByExample( E entity );

    /**
     * Find by primary key
     * @param id
     * @return unique entity
     */
    E findById( Serializable id );

    /**
     * Clear session
     */
    void clear();

    /**
     * Flush session
     */
    void flush();

    public String getOwnerUname();
    public String getOwnerUpass();
}