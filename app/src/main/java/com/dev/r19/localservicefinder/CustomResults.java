/*
*** Created By Pranjal Das on 17/03/2019
 */
package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomResults extends AppCompatActivity {

    private  ListView listView;
    private ArrayList<ServiceProviderModel> list;
    private String city,district,profession;

    DatabaseReference ref;
    FirebaseDatabase database;

    private TextView tv,header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tv = (TextView)findViewById(R.id.no_result);
        header = (TextView)findViewById(R.id.head);

        String info = getIntent().getExtras().getString("key");
        String[] infoarray = info.split("@@");
        city = infoarray[0];
        district = infoarray[1];
        profession = infoarray[2];


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomResults.this,ClientHome.class);
                startActivity(intent);
            }
        });

        listView = (ListView)findViewById(R.id.listview);
        list = new ArrayList<ServiceProviderModel>();
        final CustomViewAdapter adapter = new CustomViewAdapter(this,list);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    ServiceProviderModel model = ds.getValue(ServiceProviderModel.class);
                    if (city.equals(model.getSp_city()) && district.equals(model.getSp_district()) && profession.equals(model.getSp_proffession())) {
                        try
                        {
                            list.add(model);
                            listView.setAdapter(adapter);
                        }
                        catch (Exception e)
                        {
                          //  Toast.makeText(CustomResults.this, "No Result Found", Toast.LENGTH_SHORT).show();
                        }
                   } else {
                     //   Toast.makeText(CustomResults.this, "No Result Found", Toast.LENGTH_SHORT).show();
                    }


                }

                if(list.isEmpty())
                {
                    header.setText("No Match Found");
                    tv.setText("Click Here for Home");
                    tv.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
