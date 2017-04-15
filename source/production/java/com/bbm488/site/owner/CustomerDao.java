package com.bbm488.site.owner;

import com.bbm488.site.Database;
import com.bbm488.site.GenericDao;
import com.bbm488.site.customer.Customer;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Aybars on 9.04.2017.
 */
public interface CustomerDao extends GenericDao<String,Customer> {
    public Customer create(final String k, final Customer t);
    public Customer find(final String k);
    public Hashtable getDatabase();
    public void setDatabase(Hashtable<String, Customer> customerDB);
    public void initDatabase();
    public void delete(final String k);
}