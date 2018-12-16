package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ServiceHomePage extends AppCompatActivity {

    FirebaseAuth auth;

    Button logout, submit;

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.service_manu, menu);
        return true;
    }

        public boolean onOptionsItemSelected(android.view.MenuItem item) {
            // Handle item selection
            switch (item.getItemId()) {
                case R.id.profile_update:
                    addUpdate();
                    return true;
                case R.id.ProfSetting:
                    startSetting();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_home_page);

        auth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.ServiceSignOut);
        submit = (Button)findViewById(R.id.ToAddProfile);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceHomePage.this, ServiceInfoInsert.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                FirebaseUser user = auth.getCurrentUser();

                if (user == null) {
                    Intent intent = new Intent(ServiceHomePage.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
    public void addUpdate() {
    }

    public void startSetting() {

        }
}
