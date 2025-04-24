package com.example.DevCollege.DTO;


import jakarta.validation.constraints.*;

public class CourseDto {

    String courseId;

    @NotBlank(message = "Course Name is mandatory")
    @Size(min = 3,max = 50,message = "Course Name must be between 3 and 50 character")
    private String name;

    @NotBlank(message = "Course description is required")
    private String description;

    @NotNull(message = "Number of slots is required")
    @Min(value = 1, message = "At least one slots should be there")
    private Integer noOfSlot;


    @NotNull(message = "Course fee is required")
    @Min(value = 1000,message = "Course fee should not less than 1000")
    private Float fee;

    @NotNull(message = "Course duration is required")
    @Min(value = 60, message = "Course duration should not be less than 60 minutes")
    private Integer duration;

    @NotBlank(message = "Course tag is required")
    private String tag;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNoOfSlot() {
        return noOfSlot;
    }

    public void setNoOfSlot(Integer noOfSlot) {
        this.noOfSlot = noOfSlot;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
