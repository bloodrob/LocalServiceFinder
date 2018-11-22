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
import com.google.firebase.auth.FirebaseAuth;

public class ServiceForgotPassword extends AppCompatActivity {

    Button resetPass;
    EditText rEmail;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_forgot_password);

        auth = FirebaseAuth.getInstance();

        resetPass = (Button)findViewById(R.id.ResetPass);
        rEmail = (EditText) findViewById(R.id.Remail);

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Remail = rEmail.getText().toString().trim();

                auth.sendPasswordResetEmail(Remail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(ServiceForgotPassword.this, ServiceEmailSend.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(ServiceForgotPassword.this, "Failed To Send E-mail", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        });
            }
        });
    }
}
