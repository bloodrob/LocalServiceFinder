package com.dev.r19.localservicefinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class Results extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseDatabase database;
    ListView listView;
    ArrayList<ServiceProviderModel> list;
    CustomViewAdapter adapter;
    String Serv_name;
    TextView header,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");

        listView = (ListView)findViewById(R.id.listview);
        list = new ArrayList<ServiceProviderModel>();
        adapter = new CustomViewAdapter(this,list);

        header= (TextView)findViewById(R.id.head);
        link = (TextView)findViewById(R.id.no_result);
        Serv_name = getIntent().getExtras().getString("key");

        new LoadData().execute();

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results.this,ServiceSearch.class);
                startActivity(intent);
            }
        });
    }


    // Fetch Data in Background to show Progress Dialog
    public class LoadData extends AsyncTask<Void,Void,Integer>
    {

        private ProgressDialog dialog = new ProgressDialog(Results.this);

        @Override
        protected void onPreExecute()
        {
            try {
                dialog.setMessage("Searching...  Please Wait...");
                dialog.show();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        @Override
        protected Integer doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        try
                        {
                            ServiceProviderModel res = ds.getValue(ServiceProviderModel.class);
                            Location location = new Location("");
                            location.setLatitude(res.getSp_latitude());
                            location.setLongitude(res.getSp_longitude());


                            if(res.getSp_proffession().equals(Serv_name) && MainActivity.userLocation.distanceTo(location)<50000)
                            {
                                try {
                                    list.add(res);
                                    listView.setAdapter(adapter);
                                }
                                catch (Exception e)
                                {
                                    //  Toast.makeText(Results.this,"No Results Found",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                //  Toast.makeText(Results.this,"No Results",Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(Results.this,"Unable to Fetch Data",Toast.LENGTH_LONG).show();
                        }
                    }

                    if(list.isEmpty())
                    {
                        header.setText("No Match Found");
                        link.setText(getString(R.string.no_result));
                        link.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return 0;
        }

        @Override
        protected void onPostExecute(Integer result)
        {
            dialog.dismiss();
        }
    }
}
