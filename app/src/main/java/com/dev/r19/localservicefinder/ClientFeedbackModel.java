package com.dev.r19.localservicefinder;

/**
 * Created by R19 on 12/5/2018.
 */

public class ClientFeedbackModel {
    String Client_id, Client_name,Client_email, Message;

    public ClientFeedbackModel() {
    }

    public ClientFeedbackModel(String Client_name,String Client_email, String Message) {
        this.Client_name = Client_name;
        this.Client_email = Client_email;
        this.Message = Message;
    }
}
