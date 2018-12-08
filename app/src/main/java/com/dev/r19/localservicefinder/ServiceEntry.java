package com.dev.r19.localservicefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceEntry extends AppCompatActivity {

    public static final String TAG =ServiceEntry.class.getSimpleName() ;
    FirebaseDatabase database;
    DatabaseReference ref;

    Button submit1;
    EditText serialno1, name;

    String ServiceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_entry);

        database = FirebaseDatabase.getInstance();

        submit1 = (Button)findViewById(R.id.submit);
        serialno1 = (EditText)findViewById(R.id.Serial_no);
        name = (EditText)findViewById(R.id.cityname);

        ref = database.getReference("Service_info");
        database.getReference("app name").setValue("Location base service finder");
        database.getReference("app name").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG,"APP name Updated");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to update", databaseError.toException());

            }
        });

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Serial_no = serialno1.getText().toString().trim();
                String Service_name = name.getText().toString().trim();

                Entry(Serial_no, Service_name);

            }
        });
    }

    private void Entry(String Serial_no, String Service_name) {

            EntryService service = new EntryService(Serial_no, Service_name);
            ServiceId = ref.push().getKey();
            ref.child(ServiceId).setValue(service);
            addNewService();
    }

    private void addNewService() {
        ref.child(ServiceId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EntryService service = dataSnapshot.getValue(EntryService.class);

                Log.e(TAG, "Data is inserted" + service.Serial_no + "," +service.Service_name);
                serialno1.setText("");
                name.setText("");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to Insert data", databaseError.toException());

            }
        });
    }

}
