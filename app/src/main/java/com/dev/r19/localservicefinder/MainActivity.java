package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button asClient,asServiceProvider,add1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get Two Buttons Continue as Client and as Service Provider and add action to it.
        asClient = (Button)findViewById(R.id.AsClient);
        asServiceProvider = (Button)findViewById(R.id.AsServiceProvider);
        add1 = (Button)findViewById(R.id.add);
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
        //  Button Function Ends Here
    }
}
