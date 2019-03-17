package com.dev.r19.localservicefinder;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by R19 on 12/8/2018.
 */
@IgnoreExtraProperties
public class ServiceFeedbackModel {
    private String SP_name;
    private String SP_email;
    private String Subject;
    private String Message;

    public ServiceFeedbackModel() {
    }

    public ServiceFeedbackModel(String SP_name, String SP_email, String subject, String message) {
        this.SP_name = SP_name;
        this.SP_email = SP_email;
        this.Subject = subject;
        this.Message = message;
    }


    public String getSP_name() {
        return SP_name;
    }

    public String getSP_email() {
        return SP_email;
    }

    public String getSubject() {
        return Subject;
    }

    public String getMessage() {
        return Message;
    }
}
