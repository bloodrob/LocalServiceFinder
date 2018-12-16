package com.dev.r19.localservicefinder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceInfoInsert extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref, ref1;
    Dialog dialog;

    public static final String TAG = ServiceInfoInsert.class.getSimpleName();
    Button submit;
    EditText spemail, spname, spservicename,  spdob, spaddress, spcity, spdistrict, spstate, sppin, spmobile,  spcompanyname, spcompanydescription;
    RadioButton spmale, spfemale;
    Spinner spproffession;
    CheckBox check;
    List<String> namelist;
    String GetId;
    String Proffession;
    String Gender;
    double longitude, latitude;
    static Calendar myCalendar;
    static String DOB;
    TextView term_ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info_insert);

        term_ref = (TextView)findViewById(R.id.terms_textview);

        submit = (Button)findViewById(R.id.SPsubmit);
        spemail = (EditText)findViewById(R.id.SPemail);
        spname = (EditText)findViewById(R.id.SPname);
        spservicename = (EditText)findViewById(R.id.SPservicename);
      //  spmale = (RadioButton) findViewById(R.id.SPmale);
      //  spfemale = (RadioButton)findViewById(R.id.SPfemale);
        //for calendar
        spdob = (EditText)findViewById(R.id.SPdob);
        spdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ServiceInfoInsert.this, Bdate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        spaddress = (EditText)findViewById(R.id.SPaddress);
        spcity = (EditText)findViewById(R.id.SPcity);
        spdistrict = (EditText)findViewById(R.id.SPdistrict);
        spstate = (EditText)findViewById(R.id.SPstate);
        sppin = (EditText)findViewById(R.id.SPpin);
        spmobile = (EditText)findViewById(R.id.SPmobile);
        spproffession = (Spinner)findViewById(R.id.SPproffesion);
        spcompanyname = (EditText)findViewById(R.id.SPcompanyname);
        spcompanydescription = (EditText)findViewById(R.id.SPcompanydescription);
        check = (CheckBox)findViewById(R.id.checkbox);

        myCalendar = Calendar.getInstance();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");
      //  database.getReference("app_name").setValue("Location based service provider");



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


        // Adding Items to the Spinner *** Pranjal Das
        List<String> namelist1 = new ArrayList<>();
        namelist1.add(getString(R.string.Prof_Doctor));
        namelist1.add(getString(R.string.Prof_Lawyer));
        namelist1.add(getString(R.string.Prof_Tutor));
        namelist1.add(getString(R.string.Prof_Travel_Agent));
        namelist1.add(getString(R.string.Prof_IT_Solutions));
        namelist1.add(getString(R.string.Prof_Pharmacy));
        namelist1.add(getString(R.string.Prof_Hospital));
        namelist1.add(getString(R.string.Prof_Pathology_Lab));
        namelist1.add(getString(R.string.Prof_Photography));
        namelist1.add(getString(R.string.Prof_Insurance_Agent));
        namelist1.add(getString(R.string.Prof_Vehicle_Rent));
        namelist1.add(getString(R.string.Prof_Restaurant));
        namelist1.add(getString(R.string.Prof_Hotels));
        namelist1.add(getString(R.string.Prof_Bar_Wine_Shop));
        namelist1.add(getString(R.string.Prof_Beauty_Products));
        namelist1.add(getString(R.string.Prof_Jewellery));
        namelist1.add(getString(R.string.Prof_Grocery));
        namelist1.add(getString(R.string.Prof_Clothing));
        namelist1.add(getString(R.string.Prof_Electronics));
        namelist1.add(getString(R.string.Prof_Hardware));
        namelist1.add(getString(R.string.Prof_Showrooms));
        namelist1.add(getString(R.string.Prof_Housing));
        namelist1.add(getString(R.string.Prof_Property_Dealers));
        namelist1.add(getString(R.string.Prof_Electrician));
        namelist1.add(getString(R.string.Prof_Plumber));
        namelist1.add(getString(R.string.Prof_Carpenter));
        namelist1.add(getString(R.string.Prof_Painter));
        namelist1.add(getString(R.string.Prof_Agriculture));
        namelist1.add(getString(R.string.Prof_Transports));
        namelist1.add(getString(R.string.Prof_Others));
        namelist1.add(getString(R.string.Prof_Educational_Institute));
        namelist1.add(getString(R.string.Prof_Consultancy_Firm));

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

            dialog = new Dialog(this);
        // Terms and condition
        term_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.terms_popup);
                dialog.show();
            }
        });
        // Ends here

        //end of search service node



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SP_name = spname.getText().toString().trim();
                String SP_email = spemail.getText().toString().trim();
                String Service_name = spservicename.getText().toString().trim();
        /*        String Gender = null;
                if(spmale.isSelected())
                {
                    Gender = "Male";
                }
                if(spfemale.isSelected())
                {
                    Gender = "Female";
                } */
              //  String DOB = spdob.getText().toString().trim();
                String Address = spaddress.getText().toString().trim();
                String City = spcity.getText().toString().trim();
                String District = spdistrict.getText().toString().trim();
                String State = spstate.getText().toString().trim();
                String Pin = sppin.getText().toString().trim();
                String Mobile = spmobile.getText().toString().trim();
                //validating the mobile n o
                Pattern pattern;
                Matcher matcher;
                String mobilePattern = "(0 |[0-9][0-9])?[0-9]{10}";
                pattern = Pattern.compile(mobilePattern);
                matcher = pattern.matcher(Mobile);
                if (!matcher.matches()) {
                    spmobile.setText("Mobile no not valid");
                    return;
                }
                String Company_name = spcompanyname.getText().toString().trim();
                String Company_description = spcompanydescription.getText().toString().trim();
                if (!check.isChecked()) {
                    Toast.makeText(ServiceInfoInsert.this, "You must accept the terms and condiotion before you proceed.", Toast.LENGTH_LONG).show();
                    return;
                }

                //Location A Edited By Pranjal Das

                // Build a String for Location Geocoding
                String geoString = City.concat(",").concat(District).concat(State);
                Location A= new Location(LocationManager.NETWORK_PROVIDER);
                Geocoder gc =  new Geocoder(ServiceInfoInsert.this,Locale.getDefault());
                List<android.location.Address> adr;
                try {
                    adr=gc.getFromLocationName(geoString,1);
                    if(adr==null)
                    {
                        Toast.makeText(ServiceInfoInsert.this,"No Address found",Toast.LENGTH_LONG).show();
                    }
                    Address x = adr.get(0);
                    longitude = x.getLongitude();
                    latitude = x.getLatitude();

                }
                catch (Exception e)
                {
                    Toast.makeText(ServiceInfoInsert.this,"Error",Toast.LENGTH_LONG).show();
                }


                // Ends Here By Pranjal Das


                EntryServiceInfo( SP_name, SP_email, Service_name, Gender, DOB, Address, City, District, State, Pin, Mobile, Proffession, Company_name, Company_description, latitude, longitude);
            }
        });
    }
    private void EntryServiceInfo(String SP_name, String SP_email, String Service_name, String Gender, String DOB, String Address, String City, String District, String State, String Pin, String Mobile, String Proffession, String Company_name, String Company_description, double latitude, double longitude) {
        ServiceInfoInsertModel cinfo = new ServiceInfoInsertModel(SP_name, SP_email, Service_name, Gender, DOB, Address, City, District, State, Pin, Mobile, Proffession, Company_name, Company_description,latitude, longitude);

        cinfo.active_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        GetId = cinfo.active_id;
        ref.child(GetId).setValue(cinfo);
        addServiceInfo();
    }

    private void addServiceInfo() {
        ref.child(GetId).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ServiceInfoInsertModel cinfo = dataSnapshot.getValue(ServiceInfoInsertModel.class);

                if (cinfo == null) {
                    Log.e(TAG, "Data is null");
                    return;
                }
                Log.e(TAG, "DATA is inserted" +cinfo.SP_email + "," +cinfo.SP_name + "," +cinfo.Service_name + "," + cinfo.Gender + "," + cinfo.DOB + "," +cinfo.Address + "," + cinfo.City + "," + cinfo.State + "," + cinfo.Pin + "," +cinfo.Mobile + "," + cinfo.Proffession + "," + cinfo.Company_name + "," + cinfo.Company_description);
              //  Intent intent = new Intent(ServiceInfoInsert.this, AddSuccess.class);
                //startActivity(intent);
                Toast.makeText(ServiceInfoInsert.this, " data is successfully submited", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ServiceInfoInsert.this, ProviderHome.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error is occured", databaseError.toException());
                return;

            }
        });
    }

    // Radio Buttons

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maleRadio:
                if (checked)
                    Gender = "Male";
                    Toast.makeText(ServiceInfoInsert.this,"Selected Male",Toast.LENGTH_LONG ).show();
                break;
            case R.id.femaleRadio:
                if (checked)
                    Gender = "Female";
                    Toast.makeText(ServiceInfoInsert.this,"Selected Female",Toast.LENGTH_LONG ).show();
                break;
        }
    }
    // Radio Buttons Works

    //for calender
    DatePickerDialog.OnDateSetListener Bdate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
        }
    };
    private void updateLabel() {
        String myDateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myDateFormat, Locale.ENGLISH);
        spdob.setText(sdf.format(myCalendar.getTime()));
        DOB = spdob.getText().toString().trim();
        Toast.makeText(ServiceInfoInsert.this, "date is :"+DOB, Toast.LENGTH_SHORT).show();
    }
    //end of calendar activity
}
