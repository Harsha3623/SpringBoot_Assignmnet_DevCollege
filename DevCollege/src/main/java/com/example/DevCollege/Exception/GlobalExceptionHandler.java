package com.example.DevCollege.Exception;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
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

    //if u place get method instead of post method it will handle that
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex){
        Map<String,String> error = new HashMap<>();

        error.put("Error","Http method not allowed.");
        error.put("Details:",ex.getMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(error);
    }

    //handle exception raised when there is no resource found on particular url
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException ex){

        Map<String,String> error = new HashMap<>();

        error.put("Error:","NO Method found");
        //error.put("Detail:",ex.getResourcePath());
        error.put("Detail:",ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    //sql internal server error handler
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleSqlIntegrityContraintViolationException(SQLIntegrityConstraintViolationException exception){
        Map<String,String > error  = new HashMap<>();

        error.put("Error:","Sql Integrity Constraint violation");
        error.put("Detail:",exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

}
