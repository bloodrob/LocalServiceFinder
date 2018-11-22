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

public class ClientRegister extends AppCompatActivity {

    FirebaseAuth auth;

    Button submit, log;
    EditText email, pass, pass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_register);

        auth = FirebaseAuth.getInstance();

        submit = (Button)findViewById(R.id.ClientReg);
        log = (Button)findViewById(R.id.LogIn);
        email = (EditText)findViewById(R.id.ClientEmail);
        pass = (EditText)findViewById(R.id.ClientPassword);
        pass1 = (EditText)findViewById(R.id.ClientConPassword);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientRegister.this, ClientLogin.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ClientEmail = email.getText().toString().trim();
                String ClientPassword = pass.getText().toString().trim();
                String ClientConPassword = pass1.getText().toString().trim();

                if (TextUtils.isEmpty(ClientEmail)) {
                    Toast.makeText(ClientRegister.this, "E-mail required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ClientPassword)) {
                    Toast.makeText(ClientRegister.this, "Password required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (ClientPassword.length()<8) {
                    Toast.makeText(ClientRegister.this, "Password Can't be less than 8", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ClientConPassword)) {
                    Toast.makeText(ClientRegister.this, "Can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (! ClientPassword.equals(ClientConPassword)) {
                    Toast.makeText(ClientRegister.this, "Password must be same", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(ClientEmail, ClientPassword)
                        .addOnCompleteListener(ClientRegister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(ClientRegister.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ClientRegister.this, ClientLogin.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });
    }
}
