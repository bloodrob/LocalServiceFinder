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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //   int finecode = 1;
    Double longi, lati;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    Button asClient, asService;
    LinearLayout linearLayout;
    private int dotcounts;
    private ImageView[] dots;
    final int FINE = 1;
    final int COARSE = 2;
    final int INTER = 3;
    FirebaseDatabase database;
    FirebaseAuth auth,auth1;
    DatabaseReference ref, ref1;
    String getemail;
    Handler handler;


    static Location userLocation;
    LocationManager locationManager;
    LocationListener locationListener;
    static String City;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getPermission();
        }
        // Get Runtime Permissions
        //  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        //        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE);
        //    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, COARSE);
        // }
        //     Permission permission = new Permission();
        //     permission.getPermission(this,this);
        //Request Location
        getLocation();
        // Database Qeury
        // Check your Internet First
        // Check internet Connection
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();

        if (!isConnected) {
            // Alert Here
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }
            builder.setTitle("INTERNET CONNECTIVITY")
                    .setMessage("Please Check your Internet Connection. Turn on Your Mobile Data or Wi-Fi")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Open Settings
                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            // Alert Ends
        } else {
          //  Toast.makeText(this, "Internet Available", Toast.LENGTH_LONG).show();
        }


        // Button Working Here

        asClient = (Button) findViewById(R.id.asclient);
        asService = (Button) findViewById(R.id.asService);
        linearLayout = (LinearLayout) findViewById(R.id.slider);


        asService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking the seesion state of the user
                auth1 = FirebaseAuth.getInstance();
                if (auth1.getCurrentUser() == null) {
                    Intent intent = new Intent(MainActivity.this, ServiceLogin.class);
                    startActivity(intent);
                }
                else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    getemail = user.getEmail();
                    Intent intent = new Intent(MainActivity.this, ProviderHome.class);
                    ProviderHome.Service_email = getemail;
                    startActivity(intent);
                }  //end
            }
        });

        asClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking the seesion state of the user
                auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() == null) {
                    Intent intent = new Intent(MainActivity.this, ClientLogin.class);
                    startActivity(intent);
                }
                //end
                else {
                    Intent intent = new Intent(MainActivity.this, ClientHome.class);
                    startActivity(intent);
                }
            }
        });

        // User Guide
        TextView userguide = (TextView)findViewById(R.id.usermanual);
        userguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserGuide.class);
                startActivity(intent);
            }
        });

        // Working With Fragment
        viewPager = (ViewPager) findViewById(R.id.fragment_container);

        final PagerViewAdapter pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);

        dotcounts = pagerViewAdapter.getCount();

        dots = new ImageView[dotcounts];

        for (int i = 0; i < dotcounts; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactivedot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            linearLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.activedot));

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
       /* if (userLocation == null) {
            Toast.makeText(MainActivity.this, "Location not Found", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Location Found", Toast.LENGTH_LONG).show();
        }*/



    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                        quit();
                    }
                }).create().show();
    }



    public void getLocation() {
        locationManager = (LocationManager) getSystemService(MainActivity.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            getPermission();
            return;
        }
        userLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(userLocation!=null)
        {
            City = getCity(userLocation);
            return;
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocation = location;
                if(location!=null)
                {
               //     Toast.makeText(MainActivity.this, "" + location.getLatitude() + " / " + location.getLongitude(), Toast.LENGTH_LONG).show();
                }
                City = getCity(location);
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

    // Check Permission
    public void getPermission()
    {
        if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, COARSE);
            }
            return;
        }
        else
        {

        }


        if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {

            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE);
            }
            return;
        }
        else
        {

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
                 //   Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
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
               //     Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,
                            "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String getCity(Location location)
    {
        String city="City Not Found";
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses=null;
        try {
           // Toast.makeText(MainActivity.this,"Inside GetCity : "+location.getLatitude()+" / "+location.getLongitude(),Toast.LENGTH_LONG).show();
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try {
            city = addresses.get(0).getLocality();
        }
        catch (Exception e)
        {
            city = "City Not Found";
        }
        return city;
    }

    // Quit the main Activity
    public void quit() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(start);
    }
}


