package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ServiceInfoInsert extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref, ref1;

    public static final String TAG = ServiceInfoInsert.class.getSimpleName();
    Button submit;
    EditText spemail, spname, spservicename,  spdob, spaddress, spcity, spdistrict, spstate, sppin, spmobile,  spcompanyname, spcompanydescription;
    RadioButton spmale, spfemale;
    Spinner spproffession;
    List<String> namelist;
    String GetId;
    String Proffession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info_insert);

        submit = (Button)findViewById(R.id.SPsubmit);
        spemail = (EditText)findViewById(R.id.SPemail);
        spname = (EditText)findViewById(R.id.SPname);
        spservicename = (EditText)findViewById(R.id.SPservicename);
        spmale = (RadioButton) findViewById(R.id.Male);
        spfemale = (RadioButton)findViewById(R.id.Female);
        spdob = (EditText)findViewById(R.id.SPdob);
        spaddress = (EditText)findViewById(R.id.SPaddress);
        spcity = (EditText)findViewById(R.id.SPcity);
        spdistrict = (EditText)findViewById(R.id.SPdistrict);
        spstate = (EditText)findViewById(R.id.SPstate);
        sppin = (EditText)findViewById(R.id.SPpin);
        spmobile = (EditText)findViewById(R.id.SPmobile);
        spproffession = (Spinner)findViewById(R.id.SPproffesion);
        spcompanyname = (EditText)findViewById(R.id.SPcompanyname);
        spcompanydescription = (EditText)findViewById(R.id.SPcompanydescription);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");
        database.getReference("app_name").setValue("Location based service provider");



        // for search the Service node and retrive it and use it on the spinner
        ref1 = database.getReference("Service_info");
        namelist = new ArrayList<String>();


       ref1.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               ServiceSearchModel service = dataSnapshot.getValue(ServiceSearchModel.class);
               namelist.add(service.Service_name);
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


        List<String> namelist1 = new ArrayList<>();
        namelist1.add("one");
        namelist1.add("two");
        namelist1.add("three");

        // Adding Items to the Spinner *** Pranjal Das
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,namelist1);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spproffession.setAdapter(arrayAdapter);

        spproffession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Proffession = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Ends here

        //end of search service node
        Toast.makeText(ServiceInfoInsert.this, "Value" +Proffession, Toast.LENGTH_LONG).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SP_name = spname.getText().toString().trim();
                String SP_email = spemail.getText().toString().trim();
                String Service_name = spservicename.getText().toString().trim();
                String Gender = null;
                if(spmale.isSelected())
                {
                    Gender = "Male";
                }
                if(spfemale.isSelected())
                {
                    Gender = "Female";
                }
                String DOB = spdob.getText().toString().trim();
                String Address = spaddress.getText().toString().trim();
                String City = spcity.getText().toString().trim();
                String District = spdistrict.getText().toString().trim();
                String State = spstate.getText().toString().trim();
                String Pin = sppin.getText().toString().trim();
                String Mobile = spmobile.getText().toString().trim();
                String Company_name = spcompanyname.getText().toString().trim();
                String Company_description = spcompanydescription.getText().toString().trim();

                EntryServiceInfo( SP_name, SP_email, Service_name, Gender, DOB, Address, City, District, State, Pin, Mobile, Proffession, Company_name, Company_description);
            }
        });
    }
    private void EntryServiceInfo(String SP_name, String SP_email, String Service_name, String Gender, String DOB, String Address, String City, String District, String State, String Pin, String Mobile, String Proffession, String Company_name, String Company_description) {
        ServiceInfo cinfo = new ServiceInfo(SP_name, SP_email, Service_name, Gender, DOB, Address, City, District, State, Pin, Mobile, Proffession, Company_name, Company_description);

        cinfo.active_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        GetId = cinfo.active_id;
        ref.child(GetId).setValue(cinfo);
        addServiceInfo();
    }

    private void addServiceInfo() {
        ref.child(GetId).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ServiceInfo cinfo = dataSnapshot.getValue(ServiceInfo.class);

                if (cinfo == null) {
                    Log.e(TAG, "Data is null");
                    return;
                }
                Log.e(TAG, "DATA is inserted" +cinfo.SP_email + "," +cinfo.SP_name + "," +cinfo.Service_name + "," + cinfo.Gender + "," + cinfo.DOB + "," +cinfo.Address + "," + cinfo.City + "," + cinfo.State + "," + cinfo.Pin + "," +cinfo.Mobile + "," + cinfo.Proffession + "," + cinfo.Company_name + "," + cinfo.Company_description);
                Intent intent = new Intent(ServiceInfoInsert.this, AddSuccess.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error is occured", databaseError.toException());
                return;

            }
        });
    }

}
