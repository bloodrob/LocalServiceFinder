package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ClientCredentials extends AppCompatActivity {

    Button log, reg;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_credentials);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(ClientCredentials.this, ClientHomePage.class);
            startActivity(intent);
        }

        log = (Button)findViewById(R.id.ToClientLogin);
        reg = (Button)findViewById(R.id.ToClientReg);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientCredentials.this, ClientLogin.class);
                startActivity(intent);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientCredentials.this, ClientRegister.class);
                startActivity(intent);
            }
        });
    }
}
