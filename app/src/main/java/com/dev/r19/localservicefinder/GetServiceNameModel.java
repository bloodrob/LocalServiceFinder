package com.dev.r19.localservicefinder;

/**
 * Created by R19 on 12/8/2018.
 */

public class GetServiceNameModel {
    String SP_name,SP_email, active_id;

    public GetServiceNameModel() {
    }

    public GetServiceNameModel(String SP_name,String SP_email, String active_id) {
        this.SP_name = SP_name;
        this.SP_email = SP_email;
        this.active_id = active_id;
    }
}
