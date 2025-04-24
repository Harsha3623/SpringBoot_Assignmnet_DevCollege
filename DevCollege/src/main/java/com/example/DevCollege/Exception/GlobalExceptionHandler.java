package com.example.DevCollege.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        // Extract each field error and add it to the map
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // Return errors with BAD_REQUEST (400) status
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle general validation errors (e.g., invalid enum values, etc.)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //message not readable exception
    //example if u place string value for integer value it will handle it
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> handleMessagenotReadableException(HttpMessageNotReadableException ex){
        Map<String,String> error = new HashMap<>();

        error.put("error","Malformed JSON or invalid request body. Please check the format of your request.");

        error.put("details: ",ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
