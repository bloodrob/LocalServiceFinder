package com.dev.r19.localservicefinder;

import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.dev.r19.localservicefinder.R.id.ItemList;

public class ResultInList extends AppCompatActivity {

    ListView list1;
    DatabaseReference ref;
    FirebaseDatabase database;
    ArrayAdapter<String> adaptor;
    //public static String selectItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_in_list);



        list1 = (ListView)findViewById(ItemList);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_info");
      //  ref=ref.child("Service_info");

        List<String> resList = new ArrayList<>();
        final List<String> resList1 = new ArrayList<>();
        resList.add("1");
        resList.add("3");


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ListResult res = dataSnapshot.getValue(ListResult.class);
                resList1.add(res.Service_name);
             //   for (int i =0; i<resList1.size(); i++); {
               //     Toast.makeText(ResultInList.this, " Result is :" + resList1, Toast.LENGTH_LONG).show();

               // }
                adaptor = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,resList1 );
                list1.setAdapter(adaptor);
                list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       String selectItem = (String) list1.getItemAtPosition(position);
                        Toast.makeText(ResultInList.this, "Selected item :"+selectItem, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResultInList.this, AfterSelectItem.class);
                        intent.putExtra("selectItem", selectItem);
                        startActivity(intent);
                    }
                });
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
