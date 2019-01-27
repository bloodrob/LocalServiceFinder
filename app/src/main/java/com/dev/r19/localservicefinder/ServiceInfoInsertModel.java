package com.dev.r19.localservicefinder;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by R19 on 11/22/2018.
 */
@IgnoreExtraProperties
public class ServiceInfoInsertModel {

    String active_id;
    String SP_name;
    String SP_email;
    String Service_name;
    String Gender;
    String DOB;
    String Address;
    String City;
    String District;
    String State;
    String Pin;
    String Mobile;
    String Proffession;
    String Company_name;
    String Company_description;
    double latitude;
    double longitude;

    public ServiceInfoInsertModel() {

    }

    public ServiceInfoInsertModel(String SP_name, String SP_email, String Service_name, String Gender, String DOB, String Address, String City, String District, String State, String Pin, String Mobile, String Proffession, String Company_name, String Company_Description, double latitude, double longitude) {
        //  this.active_id = active_id;t
        this.SP_name = SP_name;
        this.SP_email = SP_email;
        this.Service_name = Service_name;
        this.Gender = Gender;
        this.DOB = DOB;
        this.City = City;
        this.District = District;
        this.Address = Address;
        this.State = State;
        this.Pin = Pin;
        this.Mobile = Mobile;
        this.Proffession = Proffession;
        this.Company_name = Company_name;
        this.Company_description = Company_Description;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getActive_id() {
        return active_id;
    }

    public String getSP_name() {
        return SP_name;
    }

    public String getSP_email() {
        return SP_email;
    }

    public String getService_name() {
        return Service_name;
    }

    public String getGender() {
        return Gender;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getDistrict() {
        return District;
    }

    public String getState() {
        return State;
    }

    public String getPin() {
        return Pin;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getProffession() {
        return Proffession;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public String getCompany_description() {
        return Company_description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}