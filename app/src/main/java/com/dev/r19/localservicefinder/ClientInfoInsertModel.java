package com.dev.r19.localservicefinder;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by R19 on 12/8/2018.
 */
@IgnoreExtraProperties
public class ClientInfoInsertModel {
    String Client_id;
    String Client_Name;
    String Client_email;
    String Gender;
    String Mobile;
    String Address;

    public ClientInfoInsertModel() {
    }

    public ClientInfoInsertModel(String client_Name, String client_email, String gender, String mobile, String address) {
        Client_Name = client_Name;
        Client_email = client_email;
        Gender = gender;
        Mobile = mobile;
        Address = address;
    }

    public String getClient_id() {
        return Client_id;
    }

    public String getClient_Name() {
        return Client_Name;
    }

    public String getClient_email() {
        return Client_email;
    }

    public String getGender() {
        return Gender;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getAddress() {
        return Address;
    }
}
