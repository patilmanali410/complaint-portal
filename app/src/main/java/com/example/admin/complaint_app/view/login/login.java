package com.example.admin.complaint_app.view.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.profile.profile;

public class login extends AppCompatActivity {
    private Button login_button;
    EditText collegeId,password;
    String collegeId1,password1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        collegeId=(EditText)findViewById(R.id.collegeid);
        password=(EditText)findViewById(R.id.loginpassword);



        login_button =(Button)findViewById(R.id.dologin);

        login_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                collegeId1=collegeId.getText().toString();
                password1=password.getText().toString();

                Log.d("login","data is"+collegeId1+" "+password1);
                openProfilepage();
            }
        });

    }

    public void openProfilepage(){
        Intent intent=new Intent(this, profile.class);
        startActivity(intent);
    }
}
