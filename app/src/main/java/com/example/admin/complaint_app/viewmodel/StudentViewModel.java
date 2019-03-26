package com.example.admin.complaint_app.viewmodel;

import android.util.Log;

import com.example.admin.complaint_app.api.ApiClient;
import com.example.admin.complaint_app.api.ApiInterface.ComplaintApiInterface;
import com.example.admin.complaint_app.api.ApiInterface.StudentApi;
import com.example.admin.complaint_app.models.Complaint;
import com.example.admin.complaint_app.models.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class StudentViewModel {
    Student student;
    public List<Student> getstudent(){
        ApiClient apiClient= new ApiClient();
        Retrofit retrofit= apiClient.getApiClient();
        StudentApi studentApi= retrofit.create(StudentApi.class);
        final List<Student> studentsArray=new ArrayList<Student>();

        Log.d("retrofit","aboce enqueue");

        studentApi.getstudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()){
                    Log.d("retrofit","response code "+response.code());
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
                    Log.d("retrofit","nams is"+s.getStudentName());
                    studentsArray.add(student);
                }
            }


            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
        return  studentsArray;
    }

}
