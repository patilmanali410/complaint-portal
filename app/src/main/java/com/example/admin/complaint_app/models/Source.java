package com.example.admin.complaint_app.models;

public class Source {

    private Student student;

    private Department department;

    public Source(){

    }



    public void setStudent(Student student) {
        this.student = student;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public Student getStudent() {
        return student;
    }

    public Source(Student student, Department department) {
        this.student = student;
        this.department = department;
    }


    public Department getDepartment() {
        return department;
    }


}
