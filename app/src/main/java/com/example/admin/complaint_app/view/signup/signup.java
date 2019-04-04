package com.example.admin.complaint_app.view.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.admin.complaint_app.R;

import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.models.StudentSignup;
import com.example.admin.complaint_app.view.login.login;
import com.example.admin.complaint_app.view.main.MainActivity;
import com.example.admin.complaint_app.view.profile.profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class signup extends AppCompatActivity {
    EditText name,email,collegeID,password,mobileNo,department,year;
    String name1,email1,collegeID1,password1,mobileNo1,department1,year1;
    Button submit;
    AwesomeValidation awesomeValidation;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseFirestore db;
    Student studentObject;
    ProgressBar signupprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("StudentDatabase");


        name=(EditText)findViewById(R.id.entername);
        email=(EditText) findViewById(R.id.enteremailid);
        collegeID=(EditText) findViewById(R.id.enterid);
        password=(EditText) findViewById(R.id.enterpassword);
        mobileNo=(EditText) findViewById(R.id.entermobileno);
        submit=(Button) findViewById(R.id.signupsubmitbutton);
        department=(EditText) findViewById(R.id.enterdepartment);
        year=(EditText) findViewById(R.id.enteryear);
        db=FirebaseFirestore.getInstance();
        signupprogress=findViewById(R.id.signupprogress);

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
                department1=department.getText().toString();
                year1=year.getText().toString();
                signupprogress.setVisibility(View.VISIBLE);
                if(awesomeValidation.validate()){
                    //add student to database
                    //open profileactivity
                    validateStudent();

                }
                else{
                    Toast.makeText(signup.this,"Error",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser !=null){
            sendToMain();
        }

    }



    private void validateStudent() {
        final List<String> ids=new ArrayList<String>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot id :dataSnapshot.getChildren()){
                    ids.add(id.getKey());

                }
                if(ids.contains(collegeID1)){
                    //Enter into pass and email into authentiacation database
                    enterIntoDatabase();
                }
                else{
                    Toast.makeText(getApplicationContext(),"College ID doesn't exists",Toast.LENGTH_LONG).show();
                    Log.d("value","not exists");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("value","error is"+ databaseError.toException());
            }
        });
    }


    private void enterIntoDatabase() {
        mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    addStudentToDatabase();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addStudentToDatabase() {
        String UID=mAuth.getCurrentUser().getUid();
        studentObject=new Student(collegeID1,name1,email1,department1,year1,mobileNo1);
        db.collection("Students").document(UID).set(studentObject).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Student Added Successfully",Toast.LENGTH_LONG).show();
                    signupprogress.setVisibility(View.INVISIBLE);
                    sendToMain();
                }
                else{
                    String error=task.getException().getMessage();
                    Toast.makeText(getApplicationContext(),"error"+error,Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void sendToMain() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}