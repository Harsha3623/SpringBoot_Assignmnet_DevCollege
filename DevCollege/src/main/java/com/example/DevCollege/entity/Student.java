package com.example.DevCollege.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    private String studentId;

    private String name;

    private String highestQualification;

    private String contactNo;

    private Float walletAmount;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Float getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(Float walletAmount) {
        this.walletAmount = walletAmount;
    }

    public Student(String studentId, String name, String highestQualification, String contactNo, Float walletAmount) {
        this.studentId = studentId;
        this.name = name;
        this.highestQualification = highestQualification;
        this.contactNo = contactNo;
        this.walletAmount = walletAmount;
    }

    public Student() {
    }
}
