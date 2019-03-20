package com.example.admin.complaint_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Complaint  {

    private String complaint_id;

    private int totalVotes;

    private Source source;

    private String title;

    private String description;

    //String image;

    private Status status;

    private Date date;

    private Vote vote;

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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public String getComplaint_id() {
        return complaint_id;
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

    public Date getDate() {
        return date;
    }

    public Vote getVote() {
        return vote;
    }
}
