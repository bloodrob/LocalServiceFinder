package com.dev.r19.localservicefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewServiceProviderProfile extends AppCompatActivity {
    TextView name_tv,email_tv,gender_tv,dob_tv,address_tv,city_tv,district_tv,state_tv,pin_tv,mobile_tv,profession_tv,companyname_tv,description_tv;
    FirebaseDatabase firedata;
    DatabaseReference databaseReference;
    String userID;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service_provider_profile);

        name_tv = (TextView)findViewById(R.id.namevalue);
        email_tv = (TextView)findViewById(R.id.emailvalue);
        gender_tv = (TextView)findViewById(R.id.gendervalue);
        dob_tv = (TextView)findViewById(R.id.dobvalue);
        address_tv = (TextView)findViewById(R.id.addressvalue);
        city_tv = (TextView)findViewById(R.id.cityvalue);
        district_tv = (TextView)findViewById(R.id.districtvalue);
        state_tv = (TextView)findViewById(R.id.statevalue);
        pin_tv = (TextView)findViewById(R.id.pinvalue);
        mobile_tv = (TextView)findViewById(R.id.mobilelvalue);
        profession_tv = (TextView)findViewById(R.id.professionvalue);
        companyname_tv = (TextView)findViewById(R.id.companynamevalue);
        description_tv = (TextView)findViewById(R.id.descriptionvalue);

        firedata = FirebaseDatabase.getInstance();
        databaseReference = firedata.getReference("Service_Provider_info");

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ServiceProviderModel model = dataSnapshot.child(userID).getValue(ServiceProviderModel.class);
                try
                {
                    name_tv.setText(""+model.getSp_name());
                    mobile_tv.setText(""+model.getSp_mobile());
                    email_tv.setText(""+model.getSp_email());
                    gender_tv.setText(""+model.getSp_gender());
                    dob_tv.setText(""+model.getSp_dob());
                    address_tv.setText(""+model.getSp_address());
                    city_tv.setText(""+model.getSp_city());
                    district_tv.setText(""+model.getSp_district());
                    state_tv.setText(""+model.getSp_state());
                    pin_tv.setText(""+model.getSp_pin());
                    profession_tv.setText(""+model.getSp_proffession());
                    companyname_tv.setText(""+model.getSp_companyname());
                    description_tv.setText(""+model.getSp_companydescription());
                }
                catch (Exception e)
                {
                    Toast.makeText(ViewServiceProviderProfile.this,"Update your Profile First",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
