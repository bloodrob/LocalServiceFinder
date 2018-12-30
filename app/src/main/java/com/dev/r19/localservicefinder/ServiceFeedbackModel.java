package com.dev.r19.localservicefinder;

/**
 * Created by R19 on 12/8/2018.
 */

public class ServiceFeedbackModel {
    String SP_id;
    String SP_name;
    String SP_email;
    String Subject;
    String Message;

    public ServiceFeedbackModel() {
    }

    public ServiceFeedbackModel(String SP_name, String SP_email, String subject, String message) {
        this.SP_name = SP_name;
        this.SP_email = SP_email;
        Subject = subject;
        Message = message;
    }
}
