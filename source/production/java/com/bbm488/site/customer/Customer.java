package com.bbm488.site.customer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Aybars on 6.04.2017.
 */
@Entity
@Table(name="customer")
public class Customer implements Serializable{
    private String uname;
    private String upass;
    private String name;
    private String surname;
    private String floor;
    private String apt;
    private String room;

    @Id
    @Column(name = "uname", nullable = false, unique = true)
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Column(name = "upass")
    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "floor")
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Column(name = "apt")
    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    @Column(name = "room")
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
