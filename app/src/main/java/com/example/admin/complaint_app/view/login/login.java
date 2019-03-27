package com.example.admin.complaint_app.view.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.view.profile.profile;


import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login_button =(Button)findViewById(R.id.dologin);

        login_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openProfilepage();
            }
        });

    }

    public void openProfilepage(){
        Intent intent=new Intent(this, profile.class);
        startActivity(intent);
    }
}
