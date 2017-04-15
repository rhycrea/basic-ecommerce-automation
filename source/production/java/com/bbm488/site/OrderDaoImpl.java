package com.bbm488.site;

import com.bbm488.site.Database;
import com.bbm488.site.GenericDaoImpl;
import com.bbm488.site.Order;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * Created by Aybars on 9.04.2017.
 */

@Component("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<Integer,Order> implements OrderDao {



    @Override
    public Order find(final Integer k) {
        return (Order)getDatabase().get(k);
    }

    @Override
    public void delete(final Integer k) {
        getDatabase().remove(k);
    }

    @Override
    public Hashtable getDatabase() {
        return Database.getOrderDB();
    }

    @Override
    public void setDatabase(Hashtable<Integer, Order> orderDB) {
        Database.setOrderDB(orderDB);
    }

    @Override
    public void initDatabase() {
        Database.setOrderDB(new Hashtable<Integer,Order>(0));
    }

}
