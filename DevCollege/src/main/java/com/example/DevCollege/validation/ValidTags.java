package com.example.DevCollege.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define the annotation for validation
@Target({ ElementType.FIELD, ElementType.PARAMETER })//setting the type which it can be used
@Retention(RetentionPolicy.RUNTIME) //telling jakarta validator for runtime validation
@Constraint(validatedBy = TagValidator.class)  // Link to the validator class
public @interface ValidTags {

    String message() default "Invalid qualification tag"; // default error message

    Class<?>[] groups() default {};  // for grouping constraints

    Class<? extends Payload>[] payload() default {};  // To carry metadata about the constraint
}
