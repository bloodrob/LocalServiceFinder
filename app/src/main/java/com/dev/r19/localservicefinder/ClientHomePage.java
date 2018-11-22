package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ClientHomePage extends AppCompatActivity {

    Button logout;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);

        auth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.CliSignOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                FirebaseUser user = auth.getCurrentUser();

                if (user == null) {
                    Intent intent = new Intent(ClientHomePage.this, ClientCredentials.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
