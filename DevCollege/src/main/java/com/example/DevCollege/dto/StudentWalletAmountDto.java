package com.example.DevCollege.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class StudentWalletAmountDto {


    private String studentId;


    @NotNull(message = "Student Wallet amount is required.")
    @Min(value = 1,message = "Wallet amount should not be negative")
    private Float walletAmount;


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Float getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(Float walletAmount) {
        this.walletAmount = walletAmount;
    }
}
