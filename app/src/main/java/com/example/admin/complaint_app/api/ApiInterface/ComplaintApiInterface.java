package com.example.admin.complaint_app.api.ApiInterface;

import com.example.admin.complaint_app.models.Complaint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ComplaintApiInterface {

    @GET("complaints")    //BASE_URL/complaints
    Call<List<Complaint>> getcomplaint();

}
