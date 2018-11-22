package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClientCredentials extends AppCompatActivity {

    Button log, reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_credentials);

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
