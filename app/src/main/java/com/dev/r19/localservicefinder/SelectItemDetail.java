package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectItemDetail extends AppCompatActivity {

    //defining the variable
    TextView name, phone, address, cdescription;
    FirebaseDatabase database;
    DatabaseReference ref;
    String passname;

    //end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item_detail);

        //Geting the string i.e passed from the AfterSelectItem page throug intent
        Intent intent = getIntent();
        passname = intent.getStringExtra("SendItem");
        //end
        //Button initialization start
        name = (TextView)findViewById(R.id.spname);
        phone = (TextView)findViewById(R.id.spphone);
        address = (TextView)findViewById(R.id.spaddress);
        cdescription = (TextView)findViewById(R.id.compdesp);
        //end of button initialization

        final String nameget = passname.toString().trim();

        //firebase activity is start from here
        database = FirebaseDatabase.getInstance();

        ref = database.getReference("Service_Provider_info");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SelectItemDetailModel res = dataSnapshot.getValue(SelectItemDetailModel.class);

                if (res.SP_name.equals(nameget)) {
                    name.setText(res.SP_name);
                    phone.setText(res.Mobile);
                    address.setText(res.Address);
                    cdescription.setText(res.Company_description);
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
        //End of firebase activity

    }
}
