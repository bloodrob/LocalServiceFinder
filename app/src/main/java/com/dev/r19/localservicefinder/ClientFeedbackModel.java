package com.dev.r19.localservicefinder;

/**
 * Created by R19 on 12/5/2018.
 */

public class ClientFeedbackModel {
    String Client_id, Client_name,Client_email, Subject, Message;

    public ClientFeedbackModel() {
    }

    public ClientFeedbackModel(String Client_name,String Client_email,String Subject, String Message) {
        this.Client_name = Client_name;
        this.Client_email = Client_email;
        this.Subject = Subject;
        this.Message = Message;
    }
}
