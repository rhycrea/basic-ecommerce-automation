package com.bbm488.site;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * Created by Aybars on 7.04.2017.
 */
@Entity
@Table(name="orders")
public class Order implements Serializable{

    private static final long serialVersionUID = 4910225916550731446L;

    private int ID;
    private Product product;
    private int pcs;
    private int totalPrice;
    private  String buyer;
    private  String productName;
    private  Boolean isSent;
    private  Instant orderDate;
    private  Instant sentDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "pcs")
    public int getPcs() {
        return pcs;
    }

    public void setPcs(int pcs) {
        this.pcs = pcs;
    }

    @Column(name = "totalprice")
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "issent")
    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

    @Column(name = "orderdate")
    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "sentdate")
    public Instant getSentDate() {
        return sentDate;
    }

    public void setSentDate(Instant sentDate) {
        this.sentDate = sentDate;
    }

    @Column(name = "buyer", length = 50)
    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    @Column(name = "productname", length = 50)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
