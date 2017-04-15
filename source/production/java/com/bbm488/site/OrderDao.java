package com.bbm488.site;

import com.bbm488.site.GenericDao;
import com.bbm488.site.Order;

import java.util.Hashtable;

/**
 * Created by Aybars on 9.04.2017.
 */
public interface OrderDao extends GenericDao<Integer,Order> {
    public Order find(final Integer k);
    public Hashtable getDatabase();
    public void setDatabase(Hashtable<Integer, Order> orderDB);
    public void initDatabase();
    public void delete(final Integer k);
}