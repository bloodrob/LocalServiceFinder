package com.dev.r19.localservicefinder;

/**
 * Created by akash on 11/30/2018.
 */

public class ListResult {
        String Service_name, Serial_no, Address;

    public ListResult() {
    }

    public ListResult(String service_name, String serial_no, String Address) {
        this.Service_name = service_name;
        this.Serial_no = serial_no;
        this.Address= Address;

    }

  /*  public String getSerial_name() {
        return Serial_name;
    }

    public String getSerial_no() {
        return Serial_no;
    } */
}
