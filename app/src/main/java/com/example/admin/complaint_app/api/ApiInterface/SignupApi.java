package com.example.admin.complaint_app.api.ApiInterface;

import com.example.admin.complaint_app.models.StudentSignup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignupApi {

    @POST("signupapi/studentsignup")  //baseurl/students
    Call<StudentSignup> addStudent(@Body StudentSignup studentSignup);
}
