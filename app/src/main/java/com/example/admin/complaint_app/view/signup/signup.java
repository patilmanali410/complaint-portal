package com.example.admin.complaint_app.view.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.admin.complaint_app.view.login.login;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.view.profile.profile;

public class signup extends AppCompatActivity {
    private Button signup_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_button =(Button)findViewById(R.id.signupbutton);

        signup_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openLoginpage();
            }
        });

    }
    public void openLoginpage(){
        Intent intent=new Intent(this, login.class);
        startActivity(intent);
    }
}
