package com.dev.r19.localservicefinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ServiceLogin extends AppCompatActivity {

    FirebaseAuth auth;

    Button submit, ToReg;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_login);

        auth = FirebaseAuth.getInstance();

        submit = (Button)findViewById(R.id.ServiceLogIn);
        ToReg = (Button)findViewById(R.id.BackReg);
        email = (EditText)findViewById(R.id.SerLogEmail);
        pass = (EditText)findViewById(R.id.SerLogPass);

        ToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceLogin.this, ServiceRegister.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String SerLogEmail = email.getText().toString().trim();
                String SerLogPass = pass.getText().toString().trim();

                auth.signInWithEmailAndPassword(SerLogEmail, SerLogPass)
                        .addOnCompleteListener(ServiceLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ServiceLogin.this, "You Are logged in", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ServiceLogin.this, ServiceInfoInsert.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(ServiceLogin.this, "Failed To Log In", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        });
            }
        });
    }
}
