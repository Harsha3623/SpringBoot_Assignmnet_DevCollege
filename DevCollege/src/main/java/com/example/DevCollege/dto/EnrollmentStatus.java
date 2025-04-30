package com.example.DevCollege.dto;


import jakarta.validation.constraints.NotBlank;

public class EnrollmentStatus {


    @NotBlank(message = "Status is required.")
    private String status;


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        if(!isValidStatus(status)){
            throw  new IllegalArgumentException("Status is incorrect");
        }
        this.status = status;
    }


    private boolean isValidStatus(String status) {
        return "Completed".equalsIgnoreCase(status)||
                "In Progress".equalsIgnoreCase(status)||
                "Cancelled".equalsIgnoreCase(status)||
                "Allocated".equalsIgnoreCase(status);
    }

}
