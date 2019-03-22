package com.example.admin.complaint_app.models;

public class Student {

    private String student_id;

    private String studentName;

    private String studentDepartment;

    private String studentYear;

    private Number studentContact;

    public void setStudent_id(String student_id) {
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

    public void setStudentContact(long studentContact) {
        this.studentContact = studentContact;
    }

    public String getStudent_id() {
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
