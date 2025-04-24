package com.example.DevCollege.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class Course {

    @Id
    private String courseId;

    private String name;

    private String description;

    private Integer noOfSlot;

    private Float fee;

    private Integer duration;

    private String tag;


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

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

    public Course(String courseId, String name, String description, Integer noOfSlot, Float fee, Integer duration, String tag) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.noOfSlot = noOfSlot;
        this.fee = fee;
        this.duration = duration;
        this.tag = tag;
    }

    public Course() {
    }
}
