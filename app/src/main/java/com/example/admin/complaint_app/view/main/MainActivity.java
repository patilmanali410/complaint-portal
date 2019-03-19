package com.example.admin.complaint_app.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.login.login;
import com.example.admin.complaint_app.view.signup.signup;

public class MainActivity extends AppCompatActivity {
    private Button login_button;
    private Button signup_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login_button =(Button)findViewById(R.id.loginbutton);
        signup_button =(Button)findViewById(R.id.signupbutton);

        login_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openLoginpage();
            }
        });


        signup_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSignuppage();
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
