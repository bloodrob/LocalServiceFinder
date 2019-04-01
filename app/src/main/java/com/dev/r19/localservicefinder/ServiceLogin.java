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

public class ServiceLogin extends AppCompatActivity {

    FirebaseAuth auth;

    Button submit, ToReg,forgot;
    EditText email, pass;
    String SerLogEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_login);

        auth = FirebaseAuth.getInstance();

        submit = (Button)findViewById(R.id.ServiceLogIn);
        ToReg = (Button)findViewById(R.id.BackReg);
        forgot = (Button)findViewById(R.id.forgot_pass_ser);
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

                SerLogEmail = email.getText().toString().trim();
                String SerLogPass = pass.getText().toString().trim();

                if (TextUtils.isEmpty(SerLogEmail)) {
                    Toast.makeText(ServiceLogin.this, " E-mail Required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(SerLogPass)) {
                    Toast.makeText(ServiceLogin.this, " Password Required", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(SerLogEmail, SerLogPass)
                        .addOnCompleteListener(ServiceLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ServiceLogin.this, "Logged in", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ServiceLogin.this, ProviderHome.class);
                                    ProviderHome.Service_email = SerLogEmail;
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

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceLogin.this, ClientForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}
