package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ServiceSearch extends AppCompatActivity {

    private static final String TAG = ServiceSearch.class.getSimpleName() ;
    FirebaseDatabase database;
    DatabaseReference ref;

    Button submit;
    EditText name;
    TextView sName1,sName2, sName3, sno1, sno2, sno3;
    ListView list;
   // ArrayList<String> Service_name = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_search);

        submit = (Button)findViewById(R.id.SearchSubmit);
        name = (EditText) findViewById(R.id.EnterName);
        sName1 = (TextView) findViewById(R.id.sername1);
        sno1 = (TextView)findViewById(R.id.ser1);

        list = (ListView)findViewById(R.id.ListView);

        database = FirebaseDatabase.getInstance();
        ref= database.getReference("Service_info");

            final List<String> namelist = new ArrayList<String>();
      //  final List<String> itemList = new ArrayList<>();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  final String ServiceName = name.getText().toString().trim();

                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ServiceSearchModel service = dataSnapshot.getValue(ServiceSearchModel.class);
                        namelist.add(service.Service_name);
                        //Log.i(TAG, "Name of service =" + namelist);
                        //System.out.println("list of the name" + namelist);
                        for (int i=0 ; i<namelist.size(); i++ );
                        {
                            Toast.makeText(ServiceSearch.this, "list of service = " + namelist, Toast.LENGTH_LONG).show();
                        }
                       // ArrayList<String> Listitem1 = service.Listitem;
                      /*  ArrayList<String> Service_Name  = service.Service_name;
                        ArrayList<String > SerialNo = service.Serial_no;
                        for (int i=0; i<Service_Name.size(); i++) {
                            sName.setText((CharSequence) Service_Name);
                        }
                        for (int i=0; i<SerialNo.size(); i++) {
                            sno.setText((CharSequence) SerialNo);
                        } */

                    /*    if(service.Service_name.equals(ServiceName)) {

                            sName1.setText(service.Service_name);
                            sno1.setText(service.Serial_no);
                        }
                        else {
                            Intent intent = new Intent(ServiceSearch.this, ServiceResultNotFound.class);
                            startActivity(intent);
                        } */
                          /*  sName1.setText(service.Service_name);
                            sno1.setText(service.Serial_no);
                            sName2.setText(service.Service_name);
                            sno2.setText(service.Serial_no);
                            sName3.setText(service.Service_name);
                            sno3.setText(service.Serial_no); */


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

                        Log.e(TAG, "Failed to load the data", databaseError.toException());
                        return;

                    }
                });
            }
        });

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,namelist);
        list.setAdapter(adaptor);

    }
}
