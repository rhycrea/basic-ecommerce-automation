package com.bbm488.site.owner;

import com.bbm488.site.GenericDao;
import com.bbm488.site.customer.Customer;

/**
 * Created by Aybars on 9.04.2017.
 */
public interface CustomerDao extends GenericDao<Customer> {
    public Customer findByUname(final String uname);
}