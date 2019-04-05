package com.example.admin.complaint_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Complaint  {


    private int totalVotes;

    private Source source;

    private String title;

    private String description;

    //String image;

    private Status status;

    private String date;


    public Complaint(int totalVotes, Source source, String title, String description, Status status, String date) {
        this.totalVotes = totalVotes;
        this.source = source;
        this.title = title;
        this.description = description;
        this.status = status;
        this.date = date;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public int getTotalVotes() {
        return totalVotes;
    }

    public Source getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

}
