package com.example.admin.complaint_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Complaint  {
    @SerializedName("complaint_id")      //to match with the actual database field name
    @Expose
    private int complaint_id;

    @SerializedName("complaintTitle")
    @Expose
    private String complaintTitle;

    @SerializedName("complaintDescription")
    @Expose
    private String complaintDescription;

    @SerializedName("student_id")
    @Expose
    private int student_id;

    @SerializedName("numberOfVotes")
    @Expose
    private int numberOfVotes;

    public int getComplaint_id() {
        return complaint_id;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public int getStudent_id() {
        return student_id;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}
