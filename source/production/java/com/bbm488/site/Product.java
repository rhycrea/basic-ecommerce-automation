package com.bbm488.site;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Aybars on 7.04.2017.
 */
@Entity
@Table(name="product")
public class Product implements Serializable{

    private static final long serialVersionUID = 4910225916550731446L;

    private int ID;
    private String name;
    private int price;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name = "name", length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
