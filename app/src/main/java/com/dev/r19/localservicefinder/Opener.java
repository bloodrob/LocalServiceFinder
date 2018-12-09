package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Opener extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opener);


        Thread mythread = new Thread(){

            @Override
            public void run() {
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
