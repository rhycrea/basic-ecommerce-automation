package com.bbm488.site.owner;

import com.bbm488.site.GenericDao;
import com.bbm488.site.Product;
import com.bbm488.site.customer.Customer;

import java.util.Hashtable;

/**
 * Created by Aybars on 9.04.2017.
 */
public interface ProductDao extends GenericDao<Integer,Product> {
    public Product find(final Integer k);
    public Hashtable getDatabase();
    public void setDatabase(Hashtable<Integer, Product> productDB);
    public void initDatabase();
    public void delete(final Integer k);
}