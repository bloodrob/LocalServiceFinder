package com.dev.r19.localservicefinder;

//package info.adroidhive.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ClientLogin extends AppCompatActivity {

    public static final String TAG = "ClientLogin";
    EditText Uemail1, Upassword1;
    Button UlogIn1, btn1, btn2;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);

        auth = FirebaseAuth.getInstance();
        //Button btn1 = (Button) findViewById(R.id.UlogIn);
        btn1 =(Button)findViewById(R.id.forgot_pass);
        btn2 = (Button) findViewById(R.id.button4);
        Uemail1 = (EditText) findViewById(R.id.Uemail);
        Upassword1 = (EditText) findViewById(R.id.Upassword);
        UlogIn1 = (Button)findViewById(R.id.Userlogin);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientLogin.this, ClientForgotPassword.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientLogin.this, ClientRegister.class);
                startActivity(intent);
            }
        });
        //Listener activity for the authenticate value
        UlogIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String Uemail = Uemail1.getText().toString().trim();
                    String Upassword = Upassword1.getText().toString().trim();



                if(TextUtils.isEmpty(Uemail)) {
                    Toast.makeText(ClientLogin.this, "No email id", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(Upassword)) {
                    Toast.makeText(ClientLogin.this, "No password", Toast.LENGTH_LONG).show();
                    return;
                }
                //Firebase activity for signin user
                auth.signInWithEmailAndPassword(Uemail,Upassword)
                        .addOnCompleteListener(ClientLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful())    {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(ClientLogin.this, "Authentication failed", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Intent intent = new Intent(ClientLogin.this, ClientHome.class);
                                    startActivity(intent);
                                }

                            }
                        });
                //end of firebase activity
            }
        });
        //end of auth activity
    }
}
