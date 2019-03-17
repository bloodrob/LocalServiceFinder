package com.dev.r19.localservicefinder;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by R19 on 12/8/2018.
 */
@IgnoreExtraProperties
public class ClientInfoInsertModel {
    private String c_name;
    private String c_email;
    private String c_gender;
    private String c_mobile;
    private String c_address;

    public ClientInfoInsertModel() {
    }

    public ClientInfoInsertModel(String c_name, String c_email, String c_gender, String c_mobile, String c_address) {
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_gender = c_gender;
        this.c_mobile = c_mobile;
        this.c_address = c_address;
    }

    public String getC_name() {
        return c_name;
    }

    public String getC_email() {
        return c_email;
    }

    public String getC_gender() {
        return c_gender;
    }

    public String getC_mobile() {
        return c_mobile;
    }

    public String getC_address() {
        return c_address;
    }
}
