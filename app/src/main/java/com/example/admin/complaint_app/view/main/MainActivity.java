package com.example.admin.complaint_app.view.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.api.ApiClient;
import com.example.admin.complaint_app.api.ApiInterface.StudentApi;
import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.view.login.login;
import com.example.admin.complaint_app.view.signup.signup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Button login_button;
    private Button signup_button;
    private TextView test;


    public List<Student> studentsArray=new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test=(TextView) findViewById(R.id.test);
        test.setText("List1");
        login_button =(Button)findViewById(R.id.loginbutton);
        signup_button =(Button)findViewById(R.id.signupbutton);

        loadJSON();
        Log.d("retrofit","below loadjson");




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

    private void loadJSON() {
            ApiClient apiClient= new ApiClient();
            Retrofit retrofit= apiClient.getApiClient();
            StudentApi studentApi= retrofit.create(StudentApi.class);


            Log.d("retrofit","2nd aboce enqueue");

            studentApi.getstudents().enqueue(new Callback<List<Student>>() {
                @Override
                public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                    Student student;
                    if (response.isSuccessful()){
                        Log.d("retrofit","3rd response code "+response.code());
                    }
                    List<Student> students=response.body();
                    Log.d("retrofit","size"+students.size());
                    for (Student s:students){
                        student=new Student();
                        student.setStudent_id(s.getStudent_id());
                        student.setStudentName(s.getStudentName());
                        student.setStudentDepartment(s.getStudentDepartment());
                        student.setStudentYear(s.getStudentYear());
                        student.setStudentContact(s.getStudentContact());
                        Log.d("retrofit","4th nams is"+s.getStudentName());
                        studentsArray.add(student);
                        Log.d("retrofit","studentarray size"+studentsArray.size());
                        Log.d("StudentArray",studentsArray.get(0).getStudentName());
                        //test.setText(studentsArray.get(0).getStudentName());
                    }
                    test.setText(String.valueOf(studentsArray.size()));
                }


                @Override
                public void onFailure(Call<List<Student>> call, Throwable t) {
                    Log.d("error",t.getMessage());
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
