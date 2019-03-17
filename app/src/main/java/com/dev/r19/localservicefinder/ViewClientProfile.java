/*
*** Created By Pranjal Das On 17/03/2019
 */

package com.dev.r19.localservicefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewClientProfile extends AppCompatActivity {

    private TextView tv_name,tv_email,tv_gender,tv_mobile,tv_address;
    String userID;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_client_profile);

        tv_name = (TextView)findViewById(R.id.vcp_name);
        tv_email = (TextView)findViewById(R.id.vcp_email);
        tv_gender = (TextView)findViewById(R.id.vcp_gender);
        tv_mobile = (TextView)findViewById(R.id.vcp_mobile);
        tv_address = (TextView)findViewById(R.id.vcp_address);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference("Client_info");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ClientInfoInsertModel model = dataSnapshot.child(userID).getValue(ClientInfoInsertModel.class);
                    try
                    {
                        tv_name.setText(""+model.getC_name());
                        tv_email.setText(""+model.getC_email());
                        tv_gender.setText(""+model.getC_gender());
                        tv_mobile.setText(""+model.getC_mobile());
                        tv_address.setText(""+model.getC_address());
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(ViewClientProfile.this,"Update your Profile First",Toast.LENGTH_SHORT).show();
                    }

                    }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
