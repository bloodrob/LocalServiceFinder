package com.dev.r19.localservicefinder;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Home extends AppCompatActivity {
    private TextView curCity;
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
