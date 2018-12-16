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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //listener for the required reg variable value
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String ClientEmail = email.getText().toString().trim();
                String ClientPassword = pass.getText().toString().trim();
                String ClientConPassword = pass1.getText().toString().trim();

                //Email validation
                Pattern pattern;
                Matcher matcher;

                if (TextUtils.isEmpty(ClientEmail)) {
                    Toast.makeText(ClientRegister.this, "E-mail required", Toast.LENGTH_LONG).show();
                    return;
                }
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                pattern = Pattern.compile(emailPattern);
                matcher = pattern.matcher(ClientEmail);
                if (!matcher.matches()) {
                    Toast.makeText(ClientRegister.this, "Email-not valid", Toast.LENGTH_LONG).show();
                    return;
                }
                //end of email validation

              //start of password validation

             /*   final String expression =  "^(?=.*[A-Za-z])(?=.*[$@$!%*#?&])(?=.*[0-9]).{8,}$";
                pattern = Pattern.compile(expression);
                matcher = pattern.matcher(ClientPassword);
                if(!matcher.matches()) {
                    Toast.makeText(ClientRegister.this, "password must contain atleast one upper class, one lower class, one number and minimun 8", Toast.LENGTH_LONG).show();
                    return;
                }*/


                if (ClientPassword.length()<6) {
                    Toast.makeText(ClientRegister.this, "Password Length Must be 6 or More", Toast.LENGTH_LONG).show();
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
                //end of password validation
                //Firebase activity for user register
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
                //end of firebase register activity
            }
        }); //end of listener
    }
}
