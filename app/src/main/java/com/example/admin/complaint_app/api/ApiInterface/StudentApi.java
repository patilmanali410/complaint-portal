package com.example.admin.complaint_app.api.ApiInterface;

import android.util.Log;

import com.example.admin.complaint_app.models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentApi {
    @GET("api/students")  //baseurl/students
    Call<List<Student>> getstudents();
}
