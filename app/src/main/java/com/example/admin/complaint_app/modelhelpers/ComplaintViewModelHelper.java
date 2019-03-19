package com.example.admin.complaint_app.modelhelpers;

import com.example.admin.complaint_app.api.ApiClient;
import com.example.admin.complaint_app.api.ApiInterface.ComplaintApiInterface;
import com.example.admin.complaint_app.models.Complaint;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ComplaintViewModelHelper {
    ApiClient apiClient;
    Retrofit retrofit;
    ComplaintApiInterface complaintApiInterface;
    Complaint complaint;

    public Complaint getComplaint(){
        apiClient= new ApiClient();
        retrofit= apiClient.getApiClient();
        complaintApiInterface= retrofit.create(ComplaintApiInterface.class);
        complaint=new Complaint();

        complaintApiInterface.getcomplaint().enqueue(new Callback<List<Complaint>>() {
            @Override
            public void onResponse(Call<List<Complaint>> call, Response<List<Complaint>> response) {
                if (response.isSuccessful()){
                    //print response.code();
                }
                List<Complaint> complaints=response.body();
                for(Complaint c :complaints){
                    complaint.setComplaintDescription(c.getComplaintDescription());
                    complaint.setComplaintTitle(c.getComplaintTitle());
                    complaint.setNumberOfVotes(c.getNumberOfVotes());
                }
            }

            @Override
            public void onFailure(Call<List<Complaint>> call, Throwable t) {
                //print t.message();
            }
        });
        return complaint;
    }
}
