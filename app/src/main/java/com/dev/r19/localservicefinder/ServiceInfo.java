package com.dev.r19.localservicefinder;

import android.location.Address;

/**
 * Created by R19 on 11/22/2018.
 */

public class ServiceInfo {

    String SP_name, SP_email, Service_name, Gender, DOB, Address, City, District, State, Pin, Mobile, Telephone, Proffession, Company_name, Company_description;


    public ServiceInfo() {

    }

    public ServiceInfo(String SP_name, String SP_email, String Service_name, String Gender, String DOB, String Address, String City, String District, String State, String Pin, String Mobile, String Telephone, String Proffession, String Company_name, String Company_Description) {
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
        this.Telephone = Telephone;
        this.Proffession = Proffession;
        this.Company_name = Company_name;
        this.Company_description = Company_Description;


    }

}