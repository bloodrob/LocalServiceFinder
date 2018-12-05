package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity {
    private TextView curCity;
    private String city;
    private ImageButton selectItem;
    private Button signout;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        auth = FirebaseAuth.getInstance();
        signout = (Button)findViewById(R.id.SignOut);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                FirebaseUser user = auth.getCurrentUser();
                if (user  == null) {
                    Intent intent = new Intent(Home.this, ClientCredentials.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        selectItem = (ImageButton)findViewById(R.id.Doctor);
        selectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AfterSelectItem.class);
                AfterSelectItem.Ser_name= "Doctor";
                startActivity(intent);
            }
        });
        curCity = (TextView)findViewById(R.id.currentCity);
        getLocation(MainActivity.userLocation);
        curCity.setText(""+city);

    }

    public void getLocation(Location location)
    {
        // Reverse Geocoding
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            city = addresses.get(0).getLocality();
        }
        catch (NullPointerException e)
        {
            city = "City Not Found";
        }

        Toast.makeText(this, "Location : " + city, Toast.LENGTH_LONG).show();
    }
}
