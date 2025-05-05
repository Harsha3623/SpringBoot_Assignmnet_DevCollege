package com.example.DevCollege.exception;

import java.util.List;

public class ValidationErrorResponse {

    private List<FieldErrorResponse> error;

    public ValidationErrorResponse(List<FieldErrorResponse> error) {
        this.error = error;
    }

    public List<FieldErrorResponse> getError() {
        return error;
    }
}

