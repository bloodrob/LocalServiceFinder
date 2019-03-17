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
import android.widget.Toast;

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
    private String userID;
    List<String> sublist;
    private String s_name,s_email,s_subject,s_message;
    private String feedback_id;

    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth mAuth;

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
        sublist.add("Suggestion");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sublist);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sub.setAdapter(arrayAdapter);

        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s_subject = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_Feedback");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_name = name.getText().toString().trim();
                s_email = email.getText().toString().trim();
                s_message = message.getText().toString().trim();

              // To Keep Previous Feedbacks
                feedback_id = ref.push().getKey();

                ServiceFeedbackModel model = new ServiceFeedbackModel(s_name, s_email, s_subject, s_message);

                try
                {
                    ref.child(userID).child(feedback_id).setValue(model);
                    Toast.makeText(ServiceFeedback.this,"Thank you for your feedback",Toast.LENGTH_LONG).show();
                }
                finally {
                    Intent intent = new Intent(ServiceFeedback.this,ProviderHome.class);
                    startActivity(intent);
                }

            }
        });
    }
}
