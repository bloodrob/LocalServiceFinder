package com.dev.r19.localservicefinder;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by akash on 11/30/2018.
 */
@IgnoreExtraProperties
public class ItemSelect {
    String SP_name, SP_email, Service_name, Address, City, District, Mobile, Proffession, Company_description;
    double latitude, longitude;

    public ItemSelect() {
    }

    public ItemSelect(String SP_name, String SP_email, String Service_name, String Address, String City, String District, String Mobile, String Proffession, String Company_description, double latitude, double longitude) {
        this.SP_name = SP_name;
        this.SP_email = SP_email;
        this.Service_name = Service_name;
        this.Address = Address;
        this.City = City;
        this.District = District;
        this.Mobile = Mobile;
        this.Proffession = Proffession;
        this.Company_description = Company_description;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getDistrict() {
        return District;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getProffession() {
        return Proffession;
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
