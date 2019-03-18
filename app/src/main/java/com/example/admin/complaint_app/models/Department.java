package com.example.admin.complaint_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department {
    @SerializedName("department_id")
    @Expose
    private int department_id;

    @SerializedName("departmentName")
    @Expose
    private String departmentName;

    @SerializedName("HOD")
    @Expose
    private String HOD;


    public int getDepartment_id() {
        return department_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getHOD() {
        return HOD;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setHOD(String HOD) {
        this.HOD = HOD;
    }
}
