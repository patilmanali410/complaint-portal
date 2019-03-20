package com.example.admin.complaint_app.models;

import com.google.gson.annotations.SerializedName;

public class Status {

    private int level;

    private String statusDescription;

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public int getLevel() {
        return level;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}

