package com.example.admin.complaint_app.view.profile.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.complaint_app.R;
import com.example.admin.complaint_app.api.ApiClient;
import com.example.admin.complaint_app.api.ApiInterface.StudentApi;
import com.example.admin.complaint_app.models.Student;
import com.example.admin.complaint_app.view.profile.mRecycler.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewComplaintsFragment extends Fragment {
    public List<Student> studentsArray=new ArrayList<Student>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_viewcomplaints, container, false);
        RecyclerView rv=rootView.findViewById(R.id.viewcomplaints);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        loadJSON(this.getActivity(),rv);
        return rootView;
    }


    private void loadJSON(final FragmentActivity activity, final RecyclerView rv) {
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
                //passing stidentsname from retroft to my adapter
                String[] studentsName=new String[studentsArray.size()];
                for(int i=0;i<studentsArray.size();i++){
                    studentsName[i]=studentsArray.get(i).getStudentName();
                }
                Log.d("retrofit","after for"+studentsName.length);
                MyAdapter myAdapter=new MyAdapter(activity,studentsName);
                rv.setAdapter(myAdapter);
            }


            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("error",t.getMessage());
            }


        });
    }
}