package com.dev.r19.localservicefinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by R19 on 11/23/2018.
 */

public class ServiceSearchModel {

  //  List<ArrayList<String>> Result ;
   //  ArrayList<String> Service_name;
  //  ArrayList<String> Serial_no;
    String Service_name, Serial_no;

    public ServiceSearchModel() {

    }
  //  public  ServiceSearchModel(List<ArrayList<String>> Result) {
   // public  ServiceSearchModel(ArrayList<String> Service_name, ArrayList<String> Serial_no) {
    public ServiceSearchModel(String Service_name, String Serial_no) {
        this.Service_name = Service_name;
        this.Serial_no = Serial_no;

       // Result = new ArrayList<>();

    }
}
