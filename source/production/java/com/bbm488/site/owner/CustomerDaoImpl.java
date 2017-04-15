package com.bbm488.site.owner;

import com.bbm488.site.Database;
import com.bbm488.site.GenericDaoImpl;
import com.bbm488.site.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * Created by Aybars on 9.04.2017.
 */

@Component("customerDao")
public class CustomerDaoImpl extends GenericDaoImpl<String,Customer> implements CustomerDao{

    @Override
    public Customer create(final String k, final Customer t) {
        if(k.equals(Owner.getUname()))
            return null;
        if(getDatabase(t).containsKey(k)) {
            return null;
        }
        getDatabase(t).put(k,t);
        return t;
    }
    @Override
    public Customer update(final String k, final Customer t) {
        if(t.getUname().equals(Owner.getUname()))
            return null;
        if(t.getUname().equals(k)) {
            getDatabase(t).put(k,t);
        }
        else {
            getDatabase(t).remove(k);
            getDatabase(t).put(t.getUname(),t);
        }

        return t;
    }
    @Override
    public Customer find(final String k) {
        return (Customer)getDatabase().get(k);
    }

    @Override
    public void delete(final String k) {
        getDatabase().remove(k);
    }

    @Override
    public Hashtable getDatabase() {
        return Database.getCustomerDB();
    }

    @Override
    public void setDatabase(Hashtable<String, Customer> customerDB) {
        Database.setCustomerDB(customerDB);
    }

    @Override
    public void initDatabase() {
        Database.setCustomerDB(new Hashtable<String,Customer>(0));
    }

}
