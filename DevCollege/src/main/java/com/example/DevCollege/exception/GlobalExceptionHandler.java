package com.example.DevCollege.exception;

import com.example.DevCollege.exception.custom.CourseSlotsHandler;
import com.example.DevCollege.exception.custom.EnrollmentHandler;
import com.example.DevCollege.exception.custom.IDNotFound;
import com.example.DevCollege.exception.custom.WalletAmountInsufficient;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // Handle validation exceptions (e.g., @Valid annotations)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {


        // Return errors with BAD_REQUEST (400) status
        List<FieldErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error-> new FieldErrorResponse(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ValidationErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }


    // Handle general validation errors (e.g., invalid enum values, etc.)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolationExceptions(ConstraintViolationException ex) {

        List<FieldErrorResponse> errors = ex.getConstraintViolations()
                .stream()
                .map(violation-> new FieldErrorResponse(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .collect(Collectors.toList());


        return new ResponseEntity<>(new ValidationErrorResponse(errors), HttpStatus.BAD_REQUEST);

    }


    // Handle JSON parse errors (e.g., invalid data types, malformed JSON)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleMessageNotReadableException(HttpMessageNotReadableException ex) {


        List<FieldErrorResponse> error  = new ArrayList<>();
        if(ex.getMessage().contains("Unrecognized field")){
            error.add(new FieldErrorResponse("Unrecognized field", "Unrecognized field '" + extractUnrecognizedField(ex.getMessage()) + "' in the request body."));
             return new ResponseEntity<>( new ValidationErrorResponse(error),HttpStatus.BAD_REQUEST);

        }


        String requiredMessage = getRequiredMessaage(ex.getMessage());
        error.add(new FieldErrorResponse("Unrecognized field","Malformed JSON or invalid request body. " +requiredMessage));

        return new ResponseEntity<>(new ValidationErrorResponse(error), HttpStatus.BAD_REQUEST);
    }


    //get the required message format for the error mismatched input
    private String getRequiredMessaage(String message) {

        if(message.contains("Cannot coerce")) {

            int index = message.indexOf("Cannot coerce ");
            String[] values = (message.substring(index)).split(" ");
            String value = values[4];
            return "Cannot convert " + values[2] + " from " + value + " to " + values[6];

        }else{

            //if error message is for json parse error and invalid format
            String[] values = message.split(" ");

            return "Json Parse error for token: "+values[5];
        }
    }


    // Handle InvalidFormatException (used when Jackson can't convert a string to a number)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidFormatException(InvalidFormatException ex) {

        Map<String, String> error = new HashMap<>();
        String invalidValue = ex.getValue() != null ? ex.getValue().toString() : "null";
        String fieldName = ex.getPathReference();
        String expectedType = ex.getTargetType().getSimpleName();

        error.put("error", String.format("Invalid value '%s' for field '%s'. Expected %s but got %s",
                invalidValue, fieldName, expectedType, ex.getValue().getClass().getSimpleName()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    // Handle HttpMethodNotSupportedException (e.g., using GET instead of POST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {

        List<FieldErrorResponse> errors = List.of(
                new FieldErrorResponse("Http Method", "Http method not allowed. " + ex.getMessage())
        );

        return new ResponseEntity<>(new ValidationErrorResponse(errors), HttpStatus.METHOD_NOT_ALLOWED);

    }



    // Handle resource not found (e.g., no matching URL for the request)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ValidationErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        List<FieldErrorResponse> errors = List.of(
                new FieldErrorResponse("NoResourceFound", "No method found. " + ex.getLocalizedMessage())
        );

        return new ResponseEntity<>(new ValidationErrorResponse(errors), HttpStatus.NOT_FOUND);
    }



    // Handle SQL integrity constraint violations (e.g., foreign key violations)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleSqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {

        String originalMessage = ex.getMessage();

        String userFriendlyMessage;

        if (originalMessage.contains("Duplicate entry")) {
            //message: Duplicate entry 'HTML' for key 'course.name_UNIQUE'
            String[] parts = originalMessage.split("'");

            if (parts.length >= 2) {
                String duplicateValue = parts[1]; // 'HTML'
                userFriendlyMessage = "Duplicate entry '" + duplicateValue + "' is not allowed.";

            } else {
                userFriendlyMessage = "Duplicate entry found. Please use a unique value.";

            }

        } else {

            // fallback message
            userFriendlyMessage = "Database constraint violation occurred.";
        }

        List<FieldErrorResponse> errors = List.of(
                new FieldErrorResponse("SQL Integrity", userFriendlyMessage)
        );

        return new ResponseEntity<>(new ValidationErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }


    private String extractUnrecognizedField(String errorMessage) {

        // Look for a pattern that matches "Unrecognized field <field_name>"
        int startIdx = errorMessage.indexOf("Unrecognized field \"") + "Unrecognized field \"".length();
        int endIdx = errorMessage.indexOf("\"", startIdx);
        return errorMessage.substring(startIdx, endIdx);
    }


    //custom exceptions for id not found
    @ExceptionHandler(IDNotFound.class)
    public ResponseEntity<ValidationErrorResponse> customexception(IDNotFound exeption){

        List<FieldErrorResponse> errorResponse = List.of(
                new FieldErrorResponse(exeption.getFeild(), exeption.getMessage())
        );

        return new ResponseEntity(new ValidationErrorResponse(errorResponse),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(WalletAmountInsufficient.class)
    public ResponseEntity<ValidationErrorResponse> wallletAmountInsufficient(WalletAmountInsufficient exception){

        List<FieldErrorResponse> errorResponses = List.of(
                new FieldErrorResponse(exception.getField(), exception.getMessage())
        );

        return new ResponseEntity(new ValidationErrorResponse(errorResponses),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EnrollmentHandler.class)
    public ResponseEntity<ValidationErrorResponse> enrollmentHandler(EnrollmentHandler exception){

        List<FieldErrorResponse> errorResponses = List.of(
                new FieldErrorResponse(exception.getField(), exception.getMessage())
        );

        return new ResponseEntity(new ValidationErrorResponse(errorResponses),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CourseSlotsHandler.class)
    public ResponseEntity<ValidationErrorResponse>  courseSlotsHandler(CourseSlotsHandler exception){

        List<FieldErrorResponse> errorResponses = List.of(
                new FieldErrorResponse(exception.getField(), exception.getMessage())
        );

        return new ResponseEntity(new ValidationErrorResponse(errorResponses),HttpStatus.BAD_REQUEST);
    }



}
