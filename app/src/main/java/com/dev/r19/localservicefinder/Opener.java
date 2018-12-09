package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Opener extends AppCompatActivity {
    TextView ex,ad;
    RelativeLayout rel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opener);

        ex = (TextView)findViewById(R.id.explore);
        ad = (TextView)findViewById(R.id.advertise);
        rel = (RelativeLayout)findViewById(R.id.rela);

        Thread mythread = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(Opener.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        mythread.start();
    }
}
