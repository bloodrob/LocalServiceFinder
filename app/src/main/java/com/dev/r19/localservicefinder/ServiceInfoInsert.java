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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class   ServiceInfoInsert extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    Dialog dialog;

    public static final String TAG = ServiceInfoInsert.class.getSimpleName();
    Button submit;
    private  EditText spemail, spname, spservicename,  spdob, spaddress, spcity, spdistrict, spstate, sppin, spmobile, spcompanyname, spcompanydescription;
    RadioButton spmale, spfemale;
    Spinner spproffession;
    CheckBox check;
    String GetId;

    // Fields to be Added
    private String sp_email;
    private String sp_name;
    private String sp_servicename;
    private String sp_gender;
    private String sp_mobile;
    private String sp_address;
    private String sp_city;
    private String sp_district;
    private String sp_state;
    private String sp_pin;
    private String sp_proffession;
    private String sp_companyname;
    private String sp_companydescription;


    double longitude, latitude;
    static Calendar myCalendar;
    static String DOB;
    TextView term_ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info_insert);

        term_ref = (TextView)findViewById(R.id.sii_terms_textview);

        submit = (Button)findViewById(R.id.sii_submit);
        spemail = (EditText)findViewById(R.id.sii_email);
        spname = (EditText)findViewById(R.id.sii_name);
        spservicename = (EditText)findViewById(R.id.sii_servicename);
      //  spmale = (RadioButton) findViewById(R.id.SPmale);
      //  spfemale = (RadioButton)findViewById(R.id.SPfemale);
        //for calendar
        spdob = (EditText)findViewById(R.id.sii_dob);
        spdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ServiceInfoInsert.this, Bdate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        spaddress = (EditText)findViewById(R.id.sii_address);
        spcity = (EditText)findViewById(R.id.sii_city);
        spdistrict = (EditText)findViewById(R.id.sii_district);
        spstate = (EditText)findViewById(R.id.sii_state);
        sppin = (EditText)findViewById(R.id.sii_pin);
        spmobile = (EditText)findViewById(R.id.sii_mobile);
        spproffession = (Spinner)findViewById(R.id.sii_proffesion);
        spcompanyname = (EditText)findViewById(R.id.sii_companyname);
        spcompanydescription = (EditText)findViewById(R.id.sii_companydescription);
        check = (CheckBox)findViewById(R.id.sii_checkbox);

        myCalendar = Calendar.getInstance();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");
      //  database.getReference("app_name").setValue("Location based service provider");






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
                sp_proffession = parent.getItemAtPosition(position).toString();
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

                sp_name = spname.getText().toString().trim();
                sp_email = spemail.getText().toString().trim();
                sp_servicename = spservicename.getText().toString().trim();
                sp_address = spaddress.getText().toString().trim();
                sp_city = spcity.getText().toString().trim();
                sp_district = spdistrict.getText().toString().trim();
                sp_state = spstate.getText().toString().trim();
                sp_pin = sppin.getText().toString().trim();
                sp_mobile = spmobile.getText().toString().trim();
                //validating the mobile n o
                Pattern pattern;
                Matcher matcher;
                String mobilePattern = "(0 |[0-9][0-9])?[0-9]{10}";
                pattern = Pattern.compile(mobilePattern);
                matcher = pattern.matcher(sp_mobile);
                if (!matcher.matches()) {
                    spmobile.setText("Mobile no not valid");
                    return;
                }
                sp_companyname = spcompanyname.getText().toString().trim();
                sp_companydescription = spcompanydescription.getText().toString().trim();
                if (!check.isChecked()) {
                    Toast.makeText(ServiceInfoInsert.this, "You must accept the terms and condiotion before you proceed.", Toast.LENGTH_LONG).show();
                    return;
                }

                //Location A Edited By Pranjal Das

                // Build a String for Location Geocoding
                String geoString = sp_city.concat(",").concat(sp_district).concat(",").concat(sp_state);

                List<android.location.Address> add = getAddress(geoString);
                if(add !=null)
                {
                    Address curadress = add.get(0);
                    longitude = curadress.getLongitude();
                    latitude = curadress.getLatitude();
                }

                // Edited By Pranjal Das on 17/03/2019
                ServiceProviderModel model = new ServiceProviderModel(sp_email, sp_name, sp_servicename, sp_gender, DOB, sp_address,  sp_city, sp_district, sp_state, sp_pin, sp_mobile, sp_proffession, sp_companyname, sp_companydescription, latitude,longitude);

                GetId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                try{
                    ref.child(GetId).setValue(model);
                    Toast.makeText(ServiceInfoInsert.this,"Your Profile is Updated",Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    Toast.makeText(ServiceInfoInsert.this,"Error Entering your Profile",Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ServiceInfoInsert.this,ProviderHome.class);
                startActivity(intent);


                               // Ends Here By Pranjal Das
            }
        });
    }

    // Radio Buttons

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sii_maleRadio:
                if (checked)
                    sp_gender = "Male";
                  //  Toast.makeText(ServiceInfoInsert.this,"Selected Male",Toast.LENGTH_LONG ).show();
                break;
            case R.id.sii_femaleRadio:
                if (checked)
                    sp_gender = "Female";
                    //Toast.makeText(ServiceInfoInsert.this,"Selected Female",Toast.LENGTH_LONG ).show();
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
       // Toast.makeText(ServiceInfoInsert.this, "date is :"+DOB, Toast.LENGTH_SHORT).show();
    }
    //end of calendar activity

    public List<android.location.Address> getAddress(String geoString)
    {
        Location A= new Location(LocationManager.NETWORK_PROVIDER);
        Geocoder gc =  new Geocoder(ServiceInfoInsert.this,Locale.getDefault());
        List<android.location.Address> adr=null;
        try {
            adr=gc.getFromLocationName(geoString,1);
            if(adr==null)
            {
                Toast.makeText(ServiceInfoInsert.this,"No Address found",Toast.LENGTH_LONG).show();
            }
            else
            {
         //       Toast.makeText(ServiceInfoInsert.this,"Sending address list",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(ServiceInfoInsert.this,"Error Finding Exact Location",Toast.LENGTH_LONG).show();
        }

        return adr;
    }
}
