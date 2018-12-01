/* package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button asClient,asServiceProvider,add1,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get Two Buttons Continue as Client and as Service Provider and add action to it.
        asClient = (Button)findViewById(R.id.AsClient);
        asServiceProvider = (Button)findViewById(R.id.AsServiceProvider);
        add1 = (Button)findViewById(R.id.add);
        search = (Button)findViewById(R.id.ServiceSearch);
        asClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClientCredentials.class);
                startActivity(intent);
            }
        });

        asServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceCredentials.class);
                startActivity(intent);
            }
        });
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceEntry.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceSearch.class);
                startActivity(intent);
            }
        });
        //  Button Function Ends Here
    }
}
*/

/// new code

    package com.dev.r19.localservicefinder;

        import android.Manifest;
        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.support.annotation.NonNull;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //   int finecode = 1;
    Double longi,lati;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    Button asClient,asService,serviceEntry,serviceSearch;
    LinearLayout linearLayout;
    private int dotcounts;
    private ImageView[] dots;
    final int FINE=1;
    final int COARSE=2;
    final int INTER =3;

    static Location userLocation;
    LocationManager locationManager;
    LocationListener locationListener;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Runtime Permissions
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE);
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},COARSE);
            }
        //     Permission permission = new Permission();
        //     permission.getPermission(this,this);
        //Request Location
        getLocation();

        asClient = (Button)findViewById(R.id.asclient);
        asService = (Button) findViewById(R.id.asService);
        serviceEntry = (Button)findViewById(R.id.serviceEntry);
        serviceSearch = (Button)findViewById(R.id.serviceSearch);
        linearLayout = (LinearLayout)findViewById(R.id.slider);


        asClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userLocation==null)
                {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ClientCredentials.class);
                startActivity(intent);
            }
        });

        asService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceCredentials.class);
                startActivity(intent);
            }
        });

        serviceEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceEntry.class);
                startActivity(intent);
            }
        });

        serviceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceSearch.class);
                startActivity(intent);
            }
        });
        viewPager = (ViewPager) findViewById(R.id.fragment_container);

        PagerViewAdapter pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);

        dotcounts = pagerViewAdapter.getCount();

        dots = new ImageView[dotcounts];

        for(int i=0;i<dotcounts;i++)
        {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8,0,8,0);

            linearLayout.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                onChangeTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        if(userLocation==null)
        {
            Toast.makeText(MainActivity.this,"Location not Found",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Location Found",Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void getLocation()
    {
        locationManager = (LocationManager)getSystemService(MainActivity.LOCATION_SERVICE);
        userLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(userLocation!=null)
        {
            return;
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocation = location;
                if(location!=null)
                {
                    Toast.makeText(MainActivity.this, "" + location.getLatitude() + " / " + location.getLongitude(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this.locationListener);
    }


    private void onChangeTab(int i) {

        if(i==0)
        {
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));
            dots[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
            dots[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
        }
        if(i==1)
        {
            dots[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
            dots[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
        }
        if(i==2)
        {
            dots[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));
            dots[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case FINE:

                // If the permission is granted, get the location, otherwise,
                // show a Toast
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,
                            "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case COARSE:

                // If the permission is granted, get the location, otherwise,
                // show a Toast
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,
                            "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}


