package com.example.admin.complaint_app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.admin.complaint_app.modelhelpers.ComplaintViewModelHelper;
import com.example.admin.complaint_app.models.Complaint;


public class ComplaintViewModel extends ViewModel {

    private Complaint complaint;
    private ComplaintViewModelHelper complaintViewModelHelper;

    public Complaint getComplaintVal(){
        if (complaint == null){
            complaint = complaintViewModelHelper.getComplaint();
        }

        return complaint;           //this can be assed in view...
    }
}
