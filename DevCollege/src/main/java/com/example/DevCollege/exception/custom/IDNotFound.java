package com.example.DevCollege.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Resource not found")
public class IDNotFound extends RuntimeException{

    private final String feild;

    private final String message;

    public IDNotFound( String feild, String message){
        super(message);
        this.feild = feild;
        this.message = message;
    }

    public String getFeild() {
        return feild;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
