package com.educagestor360.model;

/**
 * Represents a Grade or Qualification in the EducaGestor360 system.
 * Links a student to a course with a specific score.
 */
public class Grade {

    private int gradeId;
    private int studentId; // Foreign key to User (Student)
    private int courseId;  // Foreign key to Course
    private double score;
    private String comments;

    /**
     * Default constructor.
     */
    public Grade() {
    }

    /**
     * Constructor with all fields.
     *
     * @param gradeId   The unique ID of the grade.
     * @param studentId The ID of the student who received the grade.
     * @param courseId  The ID of the course for which the grade was given.
     * @param score     The numerical score.
     * @param comments  Any comments related to the grade.
     */
    public Grade(int gradeId, int studentId, int courseId, double score, String comments) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
        this.comments = comments;
    }

    // Getters and Setters

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Grade{" +
               "gradeId=" + gradeId +
               ", studentId=" + studentId +
               ", courseId=" + courseId +
               ", score=" + score +
               ", comments='" + comments + '\'' +
               '}';
    }
}