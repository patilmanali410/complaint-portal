package com.example.admin.complaint_app.models;

public class Student {

    public Number student_id;

    public String studentName;

    public String studentDepartment;

    public String studentYear;

    public Number studentContact;

    public void setStudent_id(Number student_id) {
        this.student_id = student_id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }

    public void setStudentContact(Number studentContact) {
        this.studentContact = studentContact;
    }

    public Number getStudent_id() {
        return student_id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public Number getStudentContact() {
        return studentContact;
    }
}
