package com.example.DevCollege.response;

import com.example.DevCollege.dto.CourseDto;
import com.example.DevCollege.exception.FieldErrorResponse;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<T> {
    private List<T> data;
    private List<FieldErrorResponse> error;

    public ApiResponse(List<T> data, List<FieldErrorResponse> error) {
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(List<T> data) {
        return new ApiResponse<>(data, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(List.of(data), null);
    }

    public static <T> ApiResponse<T> failure(String field, String message) {
        return new ApiResponse<>(null, List.of(new FieldErrorResponse(field, message)));
    }

    public static <T> ApiResponse<T> failure(List<FieldErrorResponse> errors) {
        return new ApiResponse<>(null, errors);
    }

    public List<T> getData() {
        return data;
    }

    public List<FieldErrorResponse> getError() {
        return error;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setError(List<FieldErrorResponse> error) {
        this.error = error;
    }
}
