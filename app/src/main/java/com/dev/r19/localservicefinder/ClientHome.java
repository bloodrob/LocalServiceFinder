/*
    *** Created By Pranjal Das on 08/12/2018
 */
package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ClientHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*/ Added Code
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.mainContents,());
        tx.commit();
        // Ends here*/

        // Lets Test

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Set Location
        TextView loc =(TextView)findViewById(R.id.myLocation);
        loc.setText(""+MainActivity.City);

        // ImageButton Works are here
        // Doctor ImageButton
        ImageButton doctor = (ImageButton)findViewById(R.id.doctor_kD);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientHome.this, AfterSelectItem.class);
                AfterSelectItem.Ser_name = getString(R.string.Prof_Doctor);
                startActivity(intent);
            }
        });

        // ImageButton Lawyer

        // Imagebutton Tutor
        ImageButton tutor =(ImageButton)findViewById(R.id.tutor_imagebutton);
        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientHome.this, AfterSelectItem.class);
                AfterSelectItem.Ser_name = getString(R.string.Prof_Tutor);
                startActivity(intent);
            }
        });

        // Pharmacy ImageButton
        ImageButton pharmacy = (ImageButton)findViewById(R.id.pharmacy_imagebutton);
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientHome.this,AfterSelectItem.class);
                AfterSelectItem.Ser_name = "Builder";
                startActivity(intent);
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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client_home, menu);
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

        if (id == R.id.custom_search)
        {
            Toast.makeText(ClientHome.this,"This Feature is not yet Implemented",Toast.LENGTH_LONG).show();
            // Handle the camera action
        }

        else if (id == R.id.nav_gallery)
        {

        }

        else if (id == R.id.nav_slideshow) {

        }

        // Action on Logout Option
        else if (id == R.id.log_out)
        {
            // checking the loggin session of the user
            auth = FirebaseAuth.getInstance();
                    auth.signOut();
                    FirebaseUser user = auth.getCurrentUser();
                    if (user  == null) {
                        Intent intent = new Intent(ClientHome.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                }
            //end of session check activity
            Toast.makeText(ClientHome.this,"you are Logged Out",Toast.LENGTH_LONG).show();
        }

        else if (id == R.id.nav_share)
        {

        }

        else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
