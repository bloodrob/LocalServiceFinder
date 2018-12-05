package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SelectItemDetail extends AppCompatActivity {


    TextView name, phone, address;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    String passname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item_detail);

        Intent intent = getIntent();
        passname = intent.getStringExtra("NewItem");

        name = (TextView)findViewById(R.id.spname);
        phone = (TextView)findViewById(R.id.spphone);
        address = (TextView)findViewById(R.id.spaddress);

        final String nameget = passname.toString().trim();
        Coordinates point1 = new Coordinates();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");
        ref1 = database.getReference("Service_Provider_info").child("geoPoint");

        ref1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Coordinates newRes = dataSnapshot.getValue(Coordinates.class);
                Location loc = new Location("");
                loc.setLongitude(newRes.longitude);
                loc.setLatitude(newRes.latitude);
                Toast.makeText(SelectItemDetail.this, " location is "+loc.getLongitude()+","+loc.getLatitude(),Toast.LENGTH_LONG).show();

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



        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Detailselectitem res = dataSnapshot.getValue(Detailselectitem.class);



                if (res.SP_name.equals(nameget)) {
                    name.setText(res.SP_name);
                    phone.setText(res.Mobile);
                    address.setText(res.Address);
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
