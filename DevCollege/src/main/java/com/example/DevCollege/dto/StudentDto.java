package com.example.DevCollege.dto;


import jakarta.validation.constraints.*;

public class StudentDto {



    private String studentId;



    @NotBlank(message = "Student name is required")
    private String name;



    @NotBlank(message = "Student Qualification is required")
    private String highestQualification;



    @Pattern(regexp = "^[0-9]{10}$",message = "Contact number must be a 10-digit number string")
    private String contactNo;



    @NotNull(message = "Student wallet amount is required")
    @Min(value = 1,message = "wallet amount should not be negative.")
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
}
