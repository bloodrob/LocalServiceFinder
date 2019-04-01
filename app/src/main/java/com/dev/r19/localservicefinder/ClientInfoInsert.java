package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  ClientInfoInsert extends AppCompatActivity {

    public static final String TAG = ClientInfoInsertModel.class.getSimpleName();
    EditText name, email, mobile, address;
    Button submit;
    private String c_gender;
    private String userID;
    private String c_name;
    private String c_email;
    private String c_mobile;
    private String c_address;

    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info_insert);

        name = (EditText)findViewById(R.id.clientName);
        email = (EditText)findViewById(R.id.clientEmail);
      //  gender = (RadioGroup)findViewById(R.id.radioGender);
        mobile = (EditText)findViewById(R.id.clientPhone);
        address = (EditText)findViewById(R.id.clientAddress);
        submit = (Button)findViewById(R.id.submitClientInfo);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Client_info");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c_name = name.getText().toString().trim();
                c_email = email.getText().toString().trim();
                c_mobile = mobile.getText().toString().trim();
                c_address = address.getText().toString().trim();

                ClientInfoInsertModel model = new ClientInfoInsertModel(c_name,c_email,c_gender,c_mobile,c_address);
                try
                {
                    ref.child(userID).setValue(model);
                    Toast.makeText(ClientInfoInsert.this,"Your Profile is Updated",Toast.LENGTH_SHORT).show();
                }
                finally {
                    Intent intent = new Intent(ClientInfoInsert.this,ClientHome.class);
                    startActivity(intent);
                }

            }
        });
    }

    // Radio Buttons

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioMale:
                if (checked)
                    c_gender = "Male";
           //     Toast.makeText(ClientInfoInsert.this,"Selected Male",Toast.LENGTH_LONG ).show();
                break;
            case R.id.radioFemale:
                if (checked)
                    c_gender = "Female";
            //    Toast.makeText(ClientInfoInsert.this,"Selected Female",Toast.LENGTH_LONG ).show();
                break;
        }
    }
    // Radio Buttons Works
}
