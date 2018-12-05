package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientFeedback extends AppCompatActivity {

    public static final String TAG = ClientFeedbackModel.class.getSimpleName();
    EditText name,email,message;
    Button submit;
    String cli_id;

    FirebaseDatabase database;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_feedback);

        name = (EditText)findViewById(R.id.cliname);
        email = (EditText)findViewById(R.id.cliemail);
        message = (EditText)findViewById(R.id.climessage);
        submit = (Button)findViewById(R.id.feedbacksubmit);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Client_Feedback");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Client_name = name.getText().toString().trim();
                String Client_email = email.getText().toString().trim();
                String Message = message.getText().toString().trim();

                GetIdOfUser(Client_name, Client_email, Message);
            }
        });
    }
    private void GetIdOfUser(String Client_name,String Client_email,String Message) {
        ClientFeedbackModel cont = new ClientFeedbackModel(Client_name, Client_email, Message);
        cont.Client_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        cli_id = cont.Client_id;
        ref.child(cli_id).setValue(cont);
        InsertFeedback();
    }
    private void InsertFeedback() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ClientFeedbackModel cont = dataSnapshot.getValue(ClientFeedbackModel.class);
                if (cont == null) {
                    Log.e(TAG,"Data is Null" );
                    return;
                }
                Log.e(TAG, "Data is Inserted" +cont.Client_name + "," +cont.Client_email + "," +cont.Message);
                Intent intent = new Intent(ClientFeedback.this, SuccessClientFeedback.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
