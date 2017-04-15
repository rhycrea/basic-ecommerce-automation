package com.bbm488.site;

import java.io.Serializable;

/**
 * Created by Aybars on 7.04.2017.
 */
public class Product implements Serializable{
    private int ID;
    private String name;
    private int price;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
