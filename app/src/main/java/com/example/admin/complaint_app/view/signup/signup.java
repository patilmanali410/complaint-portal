package com.example.admin.complaint_app.view.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.admin.complaint_app.R;

import com.example.admin.complaint_app.models.StudentSignup;
import com.example.admin.complaint_app.view.login.login;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class signup extends AppCompatActivity {
    EditText name,email,collegeID,password,mobileNo;
    String name1,email1,collegeID1,password1,mobileNo1;
    Button submit;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        name=(EditText)findViewById(R.id.entername);
        email=(EditText) findViewById(R.id.enteremailid);
        collegeID=(EditText) findViewById(R.id.enterid);
        password=(EditText) findViewById(R.id.enterpassword);
        mobileNo=(EditText) findViewById(R.id.entermobileno);
        submit=(Button) findViewById(R.id.signupsubmitbutton);

        updateUI();


    }

    private void updateUI() {
        Log.d("retrofit","isnnide updateUI");
        String regexPassword = "(?=.[a-z])(?=.[A-Z])(?=.[\\d])(?=.[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(signup.this,R.id.entername,"[a-zA-Z\\s]+",R.string.nameerr);
        awesomeValidation.addValidation(signup.this,R.id.enteremailid, Patterns.EMAIL_ADDRESS,R.string.emailerr);
        awesomeValidation.addValidation(signup.this,R.id.loginpassword,regexPassword,R.string.passworderr);
        awesomeValidation.addValidation(signup.this,R.id.entermobileno,"[0-9]{10}",R.string.contacterr);
        //validation for college id from database---------------------------------------------------
        awesomeValidation.addValidation(signup.this, R.id.enterid,"[0-9]{9}",R.string.iderr);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name1=name.getText().toString();
                email1=email.getText().toString();
                password1=password.getText().toString();
                mobileNo1=mobileNo.getText().toString();
                collegeID1=collegeID.getText().toString();

                if(awesomeValidation.validate()){
                    //add student to database
                    //open profileactivity
                    openProfile();

                }
                else{
                    Toast.makeText(signup.this,"Error",Toast.LENGTH_SHORT);
                }
            }
        });
    }


    private void openProfile() {
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }

}