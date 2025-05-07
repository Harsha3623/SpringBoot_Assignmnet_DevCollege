package com.example.DevCollege.exception.custom;


public class CourseSlotsHandler extends RuntimeException{

    private final String field;
    private final String message;

    public CourseSlotsHandler(String field, String message){
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
