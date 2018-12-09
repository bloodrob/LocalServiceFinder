package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AfterSelectItem extends AppCompatActivity {

    ListView item2;
    DatabaseReference ref;
    FirebaseDatabase database;
    ArrayAdapter<String> adaptor;
    List<String> listRes;
    String Serv_name,gotcity,gotdistrict,gotproffesion;
    static String Ser_name;
    static String getcity,getdistrict,getproffesion,key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_select_item);
        item2 = (ListView)findViewById(R.id.ItemRes);
        listRes = new ArrayList<>();
        //gating the selected item to the string i.e pass through intent from the home.java page
        Intent intent = getIntent();
        final String key1 = key.toString().trim();
        if (key1.equals("AfterSelectItem")) {
            gotcity = getcity.toString().trim();
            gotdistrict = getdistrict.toString().trim();
            gotproffesion = getproffesion.toString().trim();
        }
        else {
         Serv_name = Ser_name.toString().trim();
        }

        //end
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");
        //Firebase activity start
       ref.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               ItemSelect res = dataSnapshot.getValue(ItemSelect.class);
                // Initializing a Location object to store the location data
               Location loc = new Location("");
               loc.setLatitude(res.latitude);
               loc.setLongitude(res.longitude);
                //end of object work
                // checking the required data retrieve condition
               if ((res.Proffession.equals(Serv_name) && MainActivity.userLocation.distanceTo(loc)<50000) || (res.City.equals(getcity) && res.District.equals(gotdistrict) && res.Proffession.equals(getproffesion))) {
                   listRes.add(res.SP_name + "\n" +res.Address + "\n" +res.City+ "\n" +res.District+ "\n"+res.Company_description+ "\n Mobile number :"+res.Mobile+ "\n E-mail :"+res.SP_email+ "\n\n");
                   adaptor = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listRes);
                   item2.setAdapter(adaptor);
                   //set a string for the selected item to be passed
                  /* item2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           String NewItem = (String) item2.getItemAtPosition(position);
                           String[] name = NewItem.split("\n");
                           String SendItem = name[0];
                           Intent intent = new Intent(AfterSelectItem.this, SelectItemDetail.class);
                           intent.putExtra("SendItem", SendItem);
                           startActivity(intent);
                       }
                   }); */
                   //end
               }
               else {
                   Toast.makeText(AfterSelectItem.this, "No provider is avaiable in your area", Toast.LENGTH_SHORT).show();
                   return;
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

        //end of Firebase activity
    }
}
