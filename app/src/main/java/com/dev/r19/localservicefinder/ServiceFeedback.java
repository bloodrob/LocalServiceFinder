package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceFeedback extends AppCompatActivity {

    public static final String TAG =ServiceFeedbackModel.class.getSimpleName() ;
    EditText name, email, message;
    Spinner sub;
    Button submit;
    String getid;
    List<String> sublist;
    String Subject;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_feedback);

        name = (EditText)findViewById(R.id.spname);
        email = (EditText)findViewById(R.id.spemail);
        sub = (Spinner)findViewById(R.id.spsubject);
        message = (EditText)findViewById(R.id.spmessage);
        submit = (Button)findViewById(R.id.submitfeedback);
        sublist = new ArrayList<>();

        sublist.add("Compliment");
        sublist.add("Comment");
        sublist.add("Report");
        sublist.add("Suggest");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sublist);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sub.setAdapter(arrayAdapter);

        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Subject = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_Feedback");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SP_name = name.getText().toString().trim();
                String SP_email = email.getText().toString().trim();

                String Message = message.getText().toString().trim();

                ServiceFeedbackEntry(SP_name, SP_email, Subject, Message);
            }
        });
    }
    private void ServiceFeedbackEntry(String SP_name, String SP_email, String Subject, String Message) {
        ServiceFeedbackModel model = new ServiceFeedbackModel(SP_name, SP_email, Subject, Message);
        model.SP_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getid = model.SP_id;
        ref.child(getid).setValue(model);
        addServiceFeedback();
    }
    private void addServiceFeedback() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ServiceFeedbackModel model = dataSnapshot.getValue(ServiceFeedbackModel.class);
                if (model == null) {
                    Log.e(TAG, "Data mising !!!");
                }
                Log.e(TAG, "Data is Inserted"+model.SP_name+ "," +model.SP_email+ "," +model.Subject+ "," +model.Message);
                Intent intent = new Intent(ServiceFeedback.this, SuccessFeedback.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
