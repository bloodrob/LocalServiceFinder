package com.dev.r19.localservicefinder;

import android.location.Address;

/**
 * Created by R19 on 11/22/2018.
 */

public class ServiceInfo {

    String active_id, SP_name, SP_email, Service_name, Gender, DOB, Address, City, District, State, Pin, Mobile, Proffession, Company_name, Company_description;


    public ServiceInfo(String SP_name, String SP_email, String service_name, String gender, String DOB, String address, String city, String district, String state, String pin, String mobile, String proffession, String company_name, String company_description) {

    }

    public ServiceInfo(String active_id, String SP_name, String SP_email, String Service_name, String Gender, String DOB, String Address, String City, String District, String State, String Pin, String Mobile, String Proffession, String Company_name, String Company_Description) {
        this.active_id = active_id;
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


    }

}