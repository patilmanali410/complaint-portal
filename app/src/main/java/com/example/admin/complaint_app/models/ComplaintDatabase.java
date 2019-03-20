package com.example.admin.complaint_app.models;

public class ComplaintDatabase {

    private int totalComplaintFiled;

    private int totalComplainrtSolved;

    private Complaint complaint;

    public void setTotalComplaintFiled(int totalComplaintFiled) {
        this.totalComplaintFiled = totalComplaintFiled;
    }

    public void setTotalComplainrtSolved(int totalComplainrtSolved) {
        this.totalComplainrtSolved = totalComplainrtSolved;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public int getTotalComplaintFiled() {
        return totalComplaintFiled;
    }

    public int getTotalComplainrtSolved() {
        return totalComplainrtSolved;
    }

    public Complaint getComplaint() {
        return complaint;
    }
}
