package com.educagestor360.model;

import java.time.LocalDate;

/**
 * Represents a Course in the EducaGestor360 system.
 */
public class Course {

    private int courseId;
    private String courseName;
    private String description;
    private int teacherId; // Foreign key to User (Teacher)
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Default constructor.
     */
    public Course() {
    }

    /**
     * Constructor with all fields.
     *
     * @param courseId    The unique ID of the course.
     * @param courseName  The name of the course.
     * @param description A brief description of the course.
     * @param teacherId   The ID of the teacher assigned to the course.
     * @param startDate   The start date of the course.
     * @param endDate     The end date of the course.
     */
    public Course(int courseId, String courseName, String description, int teacherId, LocalDate startDate, LocalDate endDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.teacherId = teacherId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Course{" +
               "courseId=" + courseId +
               ", courseName='" + courseName + '\'' +
               ", description='" + description + '\'' +
               ", teacherId=" + teacherId +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               '}';
    }
}