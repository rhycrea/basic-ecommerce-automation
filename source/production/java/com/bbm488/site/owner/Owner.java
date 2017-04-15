package com.bbm488.site.owner;

import java.io.Serializable;

/**
 * Created by Aybars on 6.04.2017.
 */
public final class Owner implements Serializable {
    private static String uname;
    private static String upass;

    public static String getUname() {
        return uname;
    }

    public static void setUname(String newuname) { uname = newuname;}

    public static String getUpass() {
        return upass;
    }

    public static void setUpass(String newupass) {
        upass = newupass;
    }
}
