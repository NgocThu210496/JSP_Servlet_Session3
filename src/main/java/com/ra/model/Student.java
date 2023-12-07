package com.ra.model;

import java.util.Date;

public class Student {
    private int studentId;
    private String studentName;
    private int age;
    private Date birthDay;
    private boolean status;

    public Student() {
    }

    public Student(int studentId, String studentName, int age, Date birthDay, boolean status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.birthDay = birthDay;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
