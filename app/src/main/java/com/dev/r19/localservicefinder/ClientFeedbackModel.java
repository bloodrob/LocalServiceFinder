package com.dev.r19.localservicefinder;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by R19 on 12/5/2018.
 */
@IgnoreExtraProperties
public class ClientFeedbackModel {
    private String Client_name;
    private String Client_email;
    private String Subject;
    private String Message;

    public ClientFeedbackModel() {
    }

    public ClientFeedbackModel(String Client_name,String Client_email,String Subject, String Message) {
        this.Client_name = Client_name;
        this.Client_email = Client_email;
        this.Subject = Subject;
        this.Message = Message;
    }


    public String getClient_name() {
        return Client_name;
    }

    public String getClient_email() {
        return Client_email;
    }

    public String getSubject() {
        return Subject;
    }

    public String getMessage() {
        return Message;
    }
}
