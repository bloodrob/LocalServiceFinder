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
    List<String> listRes,listRes1;
    String Ser_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_select_item);

        Intent intent = getIntent();
        Ser_name = intent.getStringExtra("selectItem");
       // Toast.makeText(AfterSelectItem.this,"Strng is :"+Ser_name, Toast.LENGTH_LONG).show();
        item2 = (ListView)findViewById(R.id.ItemRes);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");


         listRes = new ArrayList<>();
        listRes1 = new ArrayList<>();
       // Toast.makeText(AfterSelectItem.this, " Res :",Toast.LENGTH_LONG).show();
        final String Serv_name = Ser_name.toString().trim();
       // Toast.makeText(AfterSelectItem.this, " Res :"+Serv_name, Toast.LENGTH_LONG).show();
       ref.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               ItemSelect res = dataSnapshot.getValue(ItemSelect.class);

               Location loc = new Location("");
               loc.setLatitude(res.latitude);
               loc.setLongitude(res.longitude);

              // listRes1.add(res.SP_name);

           /*    for (int i = 0; i<listRes.size(); i++); {
                   Toast.makeText(AfterSelectItem.this, " Res :"+listRes1, Toast.LENGTH_LONG).show();
               } */


               if (res.Proffession.equals(Serv_name) && MainActivity.userLocation.distanceTo(loc)<50000) {

                //   Toast.makeText(AfterSelectItem.this, " Res name :"+res.SP_name, Toast.LENGTH_LONG).show();
                   listRes.add(res.SP_name);

                   for (int i = 0; i<listRes.size(); i++); {
                   //    Toast.makeText(AfterSelectItem.this, " Res list :"+listRes, Toast.LENGTH_LONG).show();
                   }
                   adaptor = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listRes);
                   item2.setAdapter(adaptor);
                   item2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           String NewItem = (String) item2.getItemAtPosition(position);
                           Intent intent = new Intent(AfterSelectItem.this, SelectItemDetail.class);
                           intent.putExtra("NewItem", NewItem);
                           startActivity(intent);
                       }
                   });
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
