package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button asClient,asServiceProvider,add1,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get Two Buttons Continue as Client and as Service Provider and add action to it.
        asClient = (Button)findViewById(R.id.AsClient);
        asServiceProvider = (Button)findViewById(R.id.AsServiceProvider);
        add1 = (Button)findViewById(R.id.add);
        search = (Button)findViewById(R.id.ServiceSearch);
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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceSearch.class);
                startActivity(intent);
            }
        });
        //  Button Function Ends Here
    }
}

/*
/// new code

    package com.dev.r19.localservicefinder;

        import android.Manifest;
        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //   int finecode = 1;
    Double longi,lati;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    Button log,reg,serEntry;
    LinearLayout linearLayout;
    private int dotcounts;
    private ImageView[] dots;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //     Permission permission = new Permission();
        //     permission.getPermission(this,this);

        log = (Button)findViewById(R.id.button1);
        reg = (Button)findViewById(R.id.button2);
        serEntry = (Button)findViewById(R.id.button3);
        linearLayout = (LinearLayout)findViewById(R.id.slider);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClientCredentials.class);
                startActivity(intent);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceCredentials.class);
                startActivity(intent);
            }
        });

        serEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceEntry.class);
                startActivity(intent);
            }
        });
        viewPager = (ViewPager) findViewById(R.id.fragment_container);

        PagerViewAdapter pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);

        dotcounts = pagerViewAdapter.getCount();

        dots = new ImageView[dotcounts];

        for(int i=0;i<dotcounts;i++)
        {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8,0,8,0);

            linearLayout.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                onChangeTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void onChangeTab(int i) {

        if(i==0)
        {
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));
            dots[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
            dots[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
        }
        if(i==1)
        {
            dots[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
            dots[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
        }
        if(i==2)
        {
            dots[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.activedot));
            dots[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactivedot));
        }
    }
}*/
