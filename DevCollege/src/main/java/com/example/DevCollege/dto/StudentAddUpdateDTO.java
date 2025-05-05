package com.example.DevCollege.dto;

import com.example.DevCollege.validation.ValidTags;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StudentAddUpdateDTO {


    @NotBlank(message = "Student name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
    private String name;



    @NotBlank(message = "Student Qualification is required")
    //validation of qualification
    @ValidTags(message = "Invalid qualification tags")
    private String highestQualification;



    @Pattern(regexp = "^[0-9]{10}$",message = "Contact number must be a 10-digit number string")
    private String contactNo;



    @NotNull(message = "Student wallet amount is required")
    @Min(value = 1,message = "wallet amount should not be negative.")
    private Float walletAmount;


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
