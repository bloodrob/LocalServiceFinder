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

public class ClientForgotPassword extends AppCompatActivity {

    Button resetPass1;
    EditText Remail1;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();
        resetPass1 = (Button)findViewById(R.id.resetPass);
        Remail1 = (EditText) findViewById(R.id.Remail);
        resetPass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Remail = Remail1.getText().toString().trim();
                //firebase reset password activity start
                auth.sendPasswordResetEmail(Remail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(ClientForgotPassword.this, ClientEmailSend.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(ClientForgotPassword.this, "Failed to send the e-mail", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        });
                //End of reset activity
            }
        });

    }
}
