package com.bbm488.site.owner;

import com.bbm488.site.GenericDaoImpl;
import com.bbm488.site.customer.Customer;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aybars on 9.04.2017.
 */
@Repository
@Transactional
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao{
    @Override
    public Customer findByUname(final String uname) {
        return (Customer)getSession().createCriteria(Customer.class).add(Restrictions.eq("uname", uname)).uniqueResult();
    }
}
