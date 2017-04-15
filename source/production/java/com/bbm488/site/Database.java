package com.bbm488.site;

import com.bbm488.site.customer.Customer;
import com.bbm488.site.owner.Owner;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Aybars on 9.04.2017.
 */
@Repository
public class Database implements Serializable{
    private static volatile Hashtable<String, Customer> customerDB;
    private static volatile Hashtable<Integer, Product> productDB;
    private static volatile Hashtable<Integer, Order> orderDB;

    public static Hashtable<String, Customer> getCustomerDB() {
        return customerDB;
    }

    public static void setCustomerDB(Hashtable<String, Customer> customerDB) {Database.customerDB = customerDB;}

    public static Hashtable<Integer, Product> getProductDB() {
        return productDB;
    }

    public static void setProductDB(Hashtable<Integer, Product> productDB) {
        Database.productDB = productDB;
    }

    public static Hashtable<Integer, Order> getOrderDB() {
        return orderDB;
    }

    public static void setOrderDB(Hashtable<Integer, Order> orderDB) {
        Database.orderDB = orderDB;
    }


}
