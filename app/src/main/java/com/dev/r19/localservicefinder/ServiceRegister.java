package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ServiceRegister extends AppCompatActivity {

    FirebaseAuth auth;

    Button submit, Tologin;
    EditText email, pass, pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_register);


        auth = FirebaseAuth.getInstance();
        submit =(Button)findViewById(R.id.ServiceReg);
        Tologin =(Button)findViewById(R.id.SerLogin);
        email =(EditText)findViewById(R.id.ServiceEmail);
        pass = (EditText)findViewById(R.id.ServicePass);
        pass1 = (EditText)findViewById(R.id.ServiceConPass);

        Tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceRegister.this, ServiceLogin.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ServiceEmail = email.getText().toString().trim();
                final String ServicePass = pass.getText().toString().trim();
                String ServiceConPass = pass1.getText().toString().trim();

                if (TextUtils.isEmpty(ServiceEmail)) {
                    Toast.makeText(ServiceRegister.this, "E-mail Required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ServicePass)) {
                    Toast.makeText(ServiceRegister.this, "Password Required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (ServicePass.length()<8) {
                    Toast.makeText(ServiceRegister.this, "Password Length can't be less than 8", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ServiceConPass)) {
                    Toast.makeText(ServiceRegister.this, "Retype Your Password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (! ServicePass.equals(ServiceConPass)) {
                    Toast.makeText(ServiceRegister.this, "Password Must Be Same", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(ServiceEmail, ServicePass)
                        .addOnCompleteListener(ServiceRegister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(ServiceRegister.this,"Successfully registered to our system", Toast.LENGTH_LONG ).show();
                                    Intent intent = new Intent(ServiceRegister.this, ServiceLogin.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(ServiceRegister.this, "Failed to Register ", Toast.LENGTH_LONG).show();
                                    return;
                                }

                            }
                        });


            }
        });
    }
}
