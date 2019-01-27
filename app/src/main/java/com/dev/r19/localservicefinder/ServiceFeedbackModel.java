package com.dev.r19.localservicefinder;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by R19 on 12/8/2018.
 */
@IgnoreExtraProperties
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

    public String getSP_id() {
        return SP_id;
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
