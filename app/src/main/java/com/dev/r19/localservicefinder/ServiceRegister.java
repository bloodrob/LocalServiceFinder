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

        // Linking to the service login page
        Tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceRegister.this, ServiceLogin.class);
                startActivity(intent);
            }
        });
        //listener activity for the variables and the values for user create
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ServiceEmail = email.getText().toString().trim();
                final String ServicePass = pass.getText().toString().trim();
                String ServiceConPass = pass1.getText().toString().trim();

                // validating the email
                Pattern pattern;
                Matcher matcher;
                if (TextUtils.isEmpty(ServiceEmail)) {
                    Toast.makeText(ServiceRegister.this, "E-mail Required", Toast.LENGTH_LONG).show();
                    return;
                }
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                pattern = Pattern.compile(emailPattern);
                matcher = pattern.matcher(ServiceEmail);
                if (!matcher.matches()) {
                    Toast.makeText(ServiceRegister.this, "Email-not valid", Toast.LENGTH_LONG).show();
                    return;
                }
                //end of email validation
                //validating password

                final String expression = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
                pattern = Pattern.compile(expression);
                matcher = pattern.matcher(ServicePass);

                if (! matcher.matches()) {
                    Toast.makeText(ServiceRegister.this, "Password must have atleast one upper case, one lower case, one number, one special character and minimu length of 8 ", Toast.LENGTH_LONG).show();
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
                // End of validation

                //creating user account using the method below to firebase authentication
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
                //end of firebase user create activity

            }
        }); //end of listener acivity
    }
}
