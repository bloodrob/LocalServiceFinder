package com.dev.r19.localservicefinder;

/**
 * Created by R19 on 12/9/2018.
 */

public class CustomResultSearchListModel {
    String SP_name, SP_email, Address, City, District, Mobile, Proffession, Company_description;

    public CustomResultSearchListModel() {
    }

    public CustomResultSearchListModel(String SP_name, String SP_email, String Address, String city, String district, String mobile, String proffession, String company_description) {
        this.SP_name = SP_name;
        this.SP_email = SP_email;
        this.Address = Address;
        City = city;
        District = district;
        Mobile = mobile;
        Proffession = proffession;
        Company_description = company_description;
    }
}
