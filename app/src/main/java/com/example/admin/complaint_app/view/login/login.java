package com.example.admin.complaint_app.view.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.main.MainActivity;
import com.example.admin.complaint_app.view.profile.profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private Button login_button;
    EditText emailId,password;
    private ProgressBar loginprogress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailId=(EditText)findViewById(R.id.emailid);
        password=(EditText)findViewById(R.id.loginpassword);
        login_button =(Button)findViewById(R.id.dologin);
        mAuth=FirebaseAuth.getInstance();
        loginprogress=(ProgressBar) findViewById(R.id.loginprogress);

        login_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                String emailID1=emailId.getText().toString();
                String password1=password.getText().toString();
                if(!TextUtils.isEmpty(emailID1) && !TextUtils.isEmpty(password1)){
                    loginprogress.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(emailID1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                sendToMain();            //send to main activity and go to profile page;
                            }
                            else {
                                String errorMessage=task.getException().getMessage();
                                Toast.makeText(login.this,"Error :"+errorMessage,Toast.LENGTH_LONG).show();
                            }
                           loginprogress.setVisibility(View.INVISIBLE);
                        }

                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        //check if user is logged in or not if not then send back to main activity
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if (currentUser !=null){
            sendToMain();
        }

    }

    private void sendToMain() {
        Intent mainintent=new Intent(login.this,MainActivity.class);
        startActivity(mainintent);
        finish();
    }
}
