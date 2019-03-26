package com.example.admin.complaint_app.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.view.login.login;
import com.example.admin.complaint_app.view.signup.signup;
import com.example.admin.complaint_app.viewmodel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button login_button;
    private Button signup_button;
    private TextView test;

    private List<Student> students=new ArrayList<Student>();

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

    @Override
    protected void onStart() {
        super.onStart();
        test=findViewById(R.id.test);
        StudentViewModel studentViewModel=new StudentViewModel();
        students=studentViewModel.getstudent();
        Log.d("retrofit","mainactivity "+students.size());


        if (students.isEmpty()){
            test.setText("empty");
        }
        else{
            test.setText("not empty");
        }


        for(Student s:students){
            Log.d("retrofit","insidemainactivity"+s.getStudentName());
        }
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
