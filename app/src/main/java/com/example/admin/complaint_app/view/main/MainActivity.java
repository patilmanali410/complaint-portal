package com.example.admin.complaint_app.view.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.api.ApiClient;
import com.example.admin.complaint_app.api.ApiInterface.StudentApi;
import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.models.StudentSignup;
import com.example.admin.complaint_app.view.login.login;
import com.example.admin.complaint_app.view.signup.signup;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button login_button;
    private Button signup_button;

    RelativeLayout rellay1;
    Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        }
    };



    public List<Student> studentsArray=new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellay1=(RelativeLayout) findViewById(R.id.rellaym1);
        handler.postDelayed(runnable,2000);


        login_button =(Button)findViewById(R.id.loginbutton);
        signup_button =(Button)findViewById(R.id.signupbutton);

        signup_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSignuppage();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginpage();
            }
        });
    }

    public void openLoginpage(){
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }

    public void openSignuppage(){
        Intent intent=new Intent(this,signup.class);
        startActivity(intent);
    }

}
