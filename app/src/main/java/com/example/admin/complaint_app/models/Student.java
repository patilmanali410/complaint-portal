package com.example.admin.complaint_app.models;

import java.util.HashMap;
import java.util.Map;

public class Student {
  public String id;
  public String name;
  public String email;
  public String department;
  public String year;
  public String contact;

  public Student(){

  }

    public Student(String id, String name, String email, String department, String year,String contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.year = year;
        this.contact=contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getYear() {
        return year;
    }

    public String getContact() {
        return contact;
    }
}
