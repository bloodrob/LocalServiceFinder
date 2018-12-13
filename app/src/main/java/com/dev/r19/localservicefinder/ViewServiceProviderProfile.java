package com.dev.r19.localservicefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewServiceProviderProfile extends AppCompatActivity {
    TextView name_tv,email_tv,gender_tv,dob_tv,address_tv,city_tv,district_tv,state_tv,pin_tv,mobile_tv,profession_tv,companyname_tv,description_tv;
    String Getemail;
    FirebaseDatabase firedata;
    DatabaseReference databaseReference;
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Getemail = user.getEmail();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ServiceInfoInsertModel model = dataSnapshot.getValue(ServiceInfoInsertModel.class);
                if(Getemail.equals(model.SP_email)) {
                    name_tv.setText(""+model.SP_name);
                    email_tv.setText(""+model.SP_email);
                    gender_tv.setText(""+model.Gender);
                    dob_tv.setText(""+model.DOB);
                    address_tv.setText(""+model.Address);
                    city_tv.setText(""+model.City);
                    district_tv.setText(""+model.District);
                    state_tv.setText(""+model.State);
                    pin_tv.setText(""+model.Pin);
                    mobile_tv.setText(""+model.Mobile);
                    profession_tv.setText(""+model.Proffession);
                    companyname_tv.setText(""+model.Company_name);
                    description_tv.setText(""+model.Company_description);
                }

                if(!Getemail.equals(model.SP_email))
                {
                    name_tv.setText("Please Update your Profile First");
                    email_tv.setText(" ");
                    gender_tv.setText(" ");
                    dob_tv.setText(" ");
                    address_tv.setText(" ");
                    city_tv.setText(" ");
                    district_tv.setText(" ");
                    state_tv.setText(" ");
                    pin_tv.setText(" ");
                    mobile_tv.setText(" ");
                    profession_tv.setText(" ");
                    companyname_tv.setText(" ");
                    description_tv.setText(" ");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
