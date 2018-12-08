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
import android.widget.ListView;
import android.widget.Spinner;
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

    Button submit,submit1;
    EditText cityName, ditrictName;
    Spinner  proffessionName;
    ListView list;
    static List<String> namelist1;
    String selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_search);

        submit = (Button)findViewById(R.id.SearchSubmit);
        submit1 =(Button)findViewById(R.id.listsearch);
        cityName = (EditText) findViewById(R.id.cityname);
        ditrictName = (EditText)findViewById(R.id.districtname);
        proffessionName =(Spinner)findViewById(R.id.proffesion);
        //adding the value to the arralist
        namelist1 = new ArrayList<>();
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
        //adding list item to the spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,namelist1);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        proffessionName.setAdapter(arrayAdapter);

        proffessionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //end of passing

        //end of adding

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceSearch.this, ResultInList.class);
                startActivity(intent);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             Intent intent = new Intent(ServiceSearch.this, CustomResultSearcList.class);
                //passing the string value to the customResultSearchList page
                CustomResultSearcList.getcity = cityName.getText().toString().trim();
                CustomResultSearcList.getdistrict = ditrictName.getText().toString().trim();
                CustomResultSearcList.getproffesion = selectItem.toString().trim();
                startActivity(intent);
            }
        });



    }
}
