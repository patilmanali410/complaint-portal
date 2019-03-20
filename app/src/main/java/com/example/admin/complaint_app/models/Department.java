package com.example.admin.complaint_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department {

    private String department_id;

    private String departmentName;

    private String HODname;


    public String getDepartment_id() {
        return department_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getHOD() {
        return HODname;
    }



    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setHOD(String HOD) {
        this.HODname = HOD;
    }
}
