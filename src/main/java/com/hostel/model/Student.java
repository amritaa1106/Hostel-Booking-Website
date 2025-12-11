package com.hostel.model;
import java.sql.Timestamp;

public class Student {
    private int studentId;
    private String regNumber;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String course;
    private int year;
    private Timestamp createdAt;
    
    // Constructors
    public Student() {}
    
    public Student(String regNumber, String fullName, String email, String phone, 
                   String password, String gender, String course, int year) {
        this.regNumber = regNumber;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.course = course;
        this.year = year;
    }
    
    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getRegNumber() {
        return regNumber;
    }
    
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}