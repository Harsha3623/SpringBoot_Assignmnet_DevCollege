package com.example.DevCollege.validation;

import com.example.DevCollege.entity.Qualification;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagValidator implements ConstraintValidator<ValidTags, String> {

    // Define the allowed qualifications
    private List<String> allowedQualifications;

    @Override
    public void initialize(ValidTags constraintAnnotation) {
        // Initialize the allowed qualifications if needed
        allowedQualifications = Arrays.stream(Qualification.values())
                .map(Qualification::getQualificationName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // If tag is empty or null, let other validation annotations (like @NotBlank) handle it
        }

        // Split the value by commas and validate each qualification
        String[] tags = value.split(",");
        for (String tag : tags) {
            String trimmedTag = tag.trim(); // Trim any extra spaces around tags
            if (!allowedQualifications.contains(trimmedTag)) {
                return false; // If any tag is invalid, return false
            }
        }

        return true; // All tags are valid
    }
}
