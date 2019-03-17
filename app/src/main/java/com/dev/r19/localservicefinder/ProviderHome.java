package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProviderHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth auth;
    static String Service_email, Service_id;
    FirebaseDatabase database;
    DatabaseReference ref;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //database activity to get the Name of the Service Provider
        Intent intent = getIntent();
        final String sp_email = Service_email.toString().trim();
        final String sp_session_email = Service_email.toString().trim();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Service_Provider_info");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ServiceProviderModel model = dataSnapshot.getValue(ServiceProviderModel.class);

                if (model!=null) {
                    toolbar.setTitle(model.getSp_name());
                    Toast.makeText(ProviderHome.this, "Welcome "+model.getSp_name(), Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    toolbar.setTitle("Welcome User");
                }
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
        //End of database activity for get the name

        // Button Works Here
        ImageButton update = (ImageButton)findViewById(R.id.profile_update_imagebutton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProviderHome.this, ServiceInfoInsert.class);
                startActivity(intent);
            }
        });
        ImageButton logout = (ImageButton)findViewById(R.id.logout_imagebutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking the loggin session of the user
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                FirebaseUser user = auth.getCurrentUser();
                if (user  == null) {
                    Intent intent = new Intent(ProviderHome.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                Toast.makeText(ProviderHome.this, "You are Logged out", Toast.LENGTH_LONG).show();
                //end of session check activity
            }
        });

        ImageButton updatestatus = (ImageButton)findViewById(R.id.updatestatus_imagebutton);
        updatestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProviderHome.this,"This Features is Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton offers = (ImageButton)findViewById(R.id.offers_imagebutton);
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProviderHome.this,"This Features is Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent = new Intent(ProviderHome.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.provider_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ser_myprofile)
        {
            // Handle the camera action
            Intent intent = new Intent(ProviderHome.this,ViewServiceProviderProfile.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_ser_feedback)
        {
            Intent intent = new Intent(ProviderHome.this,ServiceFeedback.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_ser_log_out)
        {
            // checking the loggin session of the user
            auth = FirebaseAuth.getInstance();
            auth.signOut();
            FirebaseUser user = auth.getCurrentUser();
            if (user  == null) {
                Intent intent = new Intent(ProviderHome.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            //end of session check activity
        }
        else if (id == R.id.nav_share)
        {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Find Your Local Services or Advertise Yourself. Check Out this App.  https://play.google.com/store/apps/details?id=com.dev.r19.localservicefinder";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Local Service Finder");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        else if(id == R.id.nav_rate_ser)
        {
            try
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.dev.r19.localservicefinder")));
            }
            catch (android.content.ActivityNotFoundException e)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.dev.r19.localservicefinder")));
            }
        }
        else if (id == R.id.nav_aboutus)
        {
            Intent intent = new Intent(ProviderHome.this,AboutUs.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}


