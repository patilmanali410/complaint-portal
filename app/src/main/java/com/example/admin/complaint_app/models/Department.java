package com.example.admin.complaint_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department {

    //private String department_id;

    private String departmentName;

    private String HODname;




    public String getDepartmentName() {
        return departmentName;
    }

    public String getHOD() {
        return HODname;
    }

    public Department(String departmentName, String HODname) {
        this.departmentName = departmentName;
        this.HODname = HODname;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setHOD(String HOD) {
        this.HODname = HOD;
    }
}
