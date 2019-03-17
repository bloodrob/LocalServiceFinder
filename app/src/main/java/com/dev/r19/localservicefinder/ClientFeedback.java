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

public class ClientFeedback extends AppCompatActivity {

    public static final String TAG = ClientFeedbackModel.class.getSimpleName();
    EditText name,email,message;
    Spinner sub;
    Button submit;
    String cli_id;
    String c_Subject,c_name,c_email,c_message;
    String feedback_id;
    List<String > sublist;

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_feedback);

        name = (EditText)findViewById(R.id.cliname);
        email = (EditText)findViewById(R.id.cliemail);
        sub =(Spinner) findViewById(R.id.clisubject);
        message = (EditText)findViewById(R.id.climessage);
        submit = (Button)findViewById(R.id.feedbacksubmit);
        // array list for store feedback sub and add in spinner
        sublist = new ArrayList<>();
        sublist.add("Compliment");
        sublist.add("Comment");
        sublist.add("Report");
        sublist.add("Suggetion");

        ArrayAdapter<String > adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sublist);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sub.setAdapter(adapter);
        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c_Subject = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //end

        // Edited By Pranjal Das on 17/03/2019

        mAuth = FirebaseAuth.getInstance();
        cli_id = mAuth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Client_Feedback");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_name = name.getText().toString().trim();
                c_email = email.getText().toString().trim();
                c_message = message.getText().toString().trim();

                // Feedback_id is for Storing Feedback in New Node ( Without Overwriting Previous Feedback).. Keep History
                feedback_id = ref.push().getKey();

                ClientFeedbackModel model = new ClientFeedbackModel(c_name,c_email,c_Subject,c_message);
                try
                {
                    ref.child(cli_id).child(feedback_id).setValue(model);
                    Toast.makeText(ClientFeedback.this,"Thank you for your feedback",Toast.LENGTH_LONG).show();
                }
                finally {
                    Intent intent = new Intent(ClientFeedback.this,ClientHome.class);
                    startActivity(intent);
                }
            }
        });
    }
}
