package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ServiceHomePage extends AppCompatActivity {

    FirebaseAuth auth;

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_home_page);

        auth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.ServiceSignOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                FirebaseUser user = auth.getCurrentUser();

                if (user == null) {
                    Intent intent = new Intent(ServiceHomePage.this, ServiceCredentials.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
