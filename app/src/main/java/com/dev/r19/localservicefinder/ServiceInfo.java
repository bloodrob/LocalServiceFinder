package com.dev.r19.localservicefinder;

import android.location.Address;
import android.location.Location;

/**
 * Created by R19 on 11/22/2018.
 */

public class ServiceInfo {

    String active_id, SP_name, SP_email, Service_name, Gender, DOB, Address, City, District, State, Pin, Mobile,Proffession, Company_name, Company_description;
    Coordinates geoPoint;

    public ServiceInfo() {

    }

    public ServiceInfo(String SP_name, String SP_email, String Service_name, String Gender, String DOB, String Address, String City, String District, String State, String Pin, String Mobile, String Proffession, String Company_name, String Company_Description, Coordinates geoPoint) {
      //  this.active_id = active_id;
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
        this.geoPoint = geoPoint;

    }

}