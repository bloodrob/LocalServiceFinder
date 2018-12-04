package com.dev.r19.localservicefinder;

/**
 * Created by akash on 11/30/2018.
 */

public class ItemSelect {
    String SP_name, Service_name, Proffession;
    double latitude, longitude;

    public ItemSelect() {
    }

    public ItemSelect(String SP_name, String Service_name, String Proffession, double latitude, double longitude) {
        this.SP_name = SP_name;
        this.Service_name = Service_name;
        this.Proffession = Proffession;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
