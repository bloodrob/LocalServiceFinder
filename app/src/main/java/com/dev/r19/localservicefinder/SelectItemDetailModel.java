package com.dev.r19.localservicefinder;

/**
 * Created by akash on 11/30/2018.
 */

public class SelectItemDetailModel {
    String SP_name, Mobile, Address, Company_description;

    public SelectItemDetailModel() {
    }

    public SelectItemDetailModel(String SP_name, String mobile, String address, String Company_description) {
        this.SP_name = SP_name;
        Mobile = mobile;
        Address = address;
        this.Company_description = Company_description;
    }
}
