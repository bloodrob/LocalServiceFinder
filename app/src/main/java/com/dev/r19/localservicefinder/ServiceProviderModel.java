package com.dev.r19.localservicefinder;

public class ServiceProviderModel {
    private String sp_email;
    private String sp_name;
    private String sp_gender;
    private String sp_servicename;
    private String sp_dob;
    private String sp_mobile;
    private String sp_address;
    private String sp_city;
    private String sp_district;
    private String sp_state;
    private String sp_pin;
    private String sp_proffession;
    private String sp_companyname;
    private String sp_companydescription;
    private double sp_longitude;
    private double sp_latitude;

    public ServiceProviderModel(){}


    public ServiceProviderModel(String sp_email, String sp_name,String sp_servicename, String sp_gender, String sp_dob, String sp_address, String sp_city, String sp_district, String sp_state, String sp_pin,String sp_mobile, String sp_proffession,String sp_companyname,String sp_companydescription, double sp_latitude, double sp_longitude) {
        this.sp_email = sp_email;
        this.sp_name = sp_name;
        this.sp_servicename = sp_servicename;
        this.sp_gender = sp_gender;
        this.sp_dob = sp_dob;
        this.sp_address = sp_address;
        this.sp_city = sp_city;
        this.sp_district = sp_district;
        this.sp_state = sp_state;
        this.sp_pin = sp_pin;
        this.sp_mobile = sp_mobile;
        this.sp_proffession = sp_proffession;
        this.sp_companyname = sp_companyname;
        this.sp_companydescription = sp_companydescription;
        this.sp_latitude = sp_latitude;
        this.sp_longitude = sp_longitude;
    }

    public String getSp_servicename() {
        return sp_servicename;
    }

    public String getSp_dob() {
        return sp_dob;
    }

    public String getSp_companyname() {
        return sp_companyname;
    }

    public String getSp_companydescription() {
        return sp_companydescription;
    }

    public String getSp_email() {
        return sp_email;
    }

    public String getSp_name() {
        return sp_name;
    }

    public String getSp_gender() {
        return sp_gender;
    }

    public String getSp_mobile() {
        return sp_mobile;
    }

    public String getSp_address() {
        return sp_address;
    }

    public String getSp_city() {
        return sp_city;
    }

    public String getSp_district() {
        return sp_district;
    }

    public String getSp_state() {
        return sp_state;
    }

    public String getSp_pin() {
        return sp_pin;
    }

    public String getSp_proffession() {
        return sp_proffession;
    }

    public double getSp_longitude() {
        return sp_longitude;
    }

    public double getSp_latitude() {
        return sp_latitude;
    }
}
