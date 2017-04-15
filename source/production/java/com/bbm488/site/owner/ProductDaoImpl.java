package com.bbm488.site.owner;

import com.bbm488.site.Database;
import com.bbm488.site.GenericDaoImpl;
import com.bbm488.site.Product;
import org.springframework.stereotype.Component;

import java.util.Hashtable;

/**
 * Created by Aybars on 9.04.2017.
 */

@Component("productDao")
public class ProductDaoImpl extends GenericDaoImpl<Integer,Product> implements ProductDao{

    @Override
    public Product find(final Integer k) {
        return (Product)getDatabase().get(k);
    }

    @Override
    public void delete(final Integer k) {
        getDatabase().remove(k);
    }

    @Override
    public Hashtable getDatabase() {
        return Database.getProductDB();
    }

    @Override
    public void setDatabase(Hashtable<Integer, Product> productDB) {
        Database.setProductDB(productDB);
    }

    @Override
    public void initDatabase() {
        Database.setProductDB(new Hashtable<Integer,Product>(0));
    }

}
