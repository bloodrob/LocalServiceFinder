package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomResultSearcList extends AppCompatActivity {

    static String getcity, getdistrict, getproffesion;
    ListView item;
    static List<String > getItem;
    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_result_searc_list);
        item = (ListView)findViewById(R.id.searclist);

        //getting the value passed from Servicesearch page
        Intent intent = getIntent();
        final String gotcity = getcity.toString().trim();
        final String gotdistrict = getdistrict.toString().trim();
        final String gotproffession = getproffesion.toString().trim();
        //end of receive
        getItem = new ArrayList<>();
        //database activity start
        database= FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CustomResultSearchListModel model = dataSnapshot.getValue(CustomResultSearchListModel.class);
                if (gotcity.equals(model.City) && gotdistrict.equals(model.District) && gotproffession.equals(model.Proffession)) {
                    getItem.add(model.SP_name+ "\n" +model.City+ "\n"+model.Address+ "\n" +model.District+ "\n" +model.Company_description+ "\n Mobile no : " +model.Mobile+ "\n Email : " +model.SP_email+"\n\n");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(CustomResultSearcList.this, android.R.layout.simple_list_item_1,getItem);
                    item.setAdapter(adapter);
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
