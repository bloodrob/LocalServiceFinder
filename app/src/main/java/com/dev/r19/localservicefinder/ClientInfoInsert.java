package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientInfoInsert extends AppCompatActivity {

    public static final String TAG = ClientInfoInsertModel.class.getSimpleName();
    EditText name, email, mobile, address;
    RadioGroup gender;
    Button submit;
    String Gender;
    String getid;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info_insert);

        name = (EditText)findViewById(R.id.clientName);
        email = (EditText)findViewById(R.id.clientEmail);
        gender = (RadioGroup)findViewById(R.id.radioGender);
        mobile = (EditText)findViewById(R.id.clientPhone);
        address = (EditText)findViewById(R.id.clientAddress);
        submit = (Button)findViewById(R.id.submitClientInfo);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Client_info");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Client_Name = name.getText().toString().trim();
                String Client_email = email.getText().toString().trim();
                String Mobile = mobile.getText().toString().trim();
                String Address = address.getText().toString().trim();

                Entrydata(Client_Name, Client_email, Gender, Mobile, Address);

            }
        });
    }

    private void Entrydata(String Client_name, String Client_email, String Gender, String Mobile, String Address) {
        ClientInfoInsertModel model = new ClientInfoInsertModel(Client_name, Client_email, Gender, Mobile, Address);
        model.Client_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getid = model.Client_id;
        ref.child(getid).setValue(model);
        addClientInfo();

    }

    private void addClientInfo() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ClientInfoInsertModel model = dataSnapshot.getValue(ClientInfoInsertModel.class);
                if (model == null) {
                    Log.e(TAG, "Data missing !!!");
                }
                Log.e(TAG, "Data is inserted"+model.Client_Name + "," +model.Client_email+ "," +model.Gender+ "," +model.Mobile+ "," +model.Address);
                Toast.makeText(ClientInfoInsert.this, " Your Profile has been successfully updated.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ClientInfoInsert.this, ClientHome.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    // Radio Buttons

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maleRadio:
                if (checked)
                    Gender = "Male";
                Toast.makeText(ClientInfoInsert.this,"Selected Male",Toast.LENGTH_LONG ).show();
                break;
            case R.id.femaleRadio:
                if (checked)
                    Gender = "Female";
                Toast.makeText(ClientInfoInsert.this,"Selected Female",Toast.LENGTH_LONG ).show();
                break;
        }
    }
    // Radio Buttons Works
}
