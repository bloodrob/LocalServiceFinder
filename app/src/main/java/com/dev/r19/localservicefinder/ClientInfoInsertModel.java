package com.dev.r19.localservicefinder;

/**
 * Created by R19 on 12/8/2018.
 */

public class ClientInfoInsertModel {
    String Client_id,Client_Name, Client_email, Gender, Mobile, Address;

    public ClientInfoInsertModel() {
    }

    public ClientInfoInsertModel(String client_Name, String client_email, String gender, String mobile, String address) {
        Client_Name = client_Name;
        Client_email = client_email;
        Gender = gender;
        Mobile = mobile;
        Address = address;
    }
}