package com.educagestor360.dao;

import com.educagestor360.model.Grade;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) interface for Grade entities.
 * Defines standard operations to be performed on Grade objects.
 */
public interface GradeDAO {

    /**
     * Adds a new grade to the database.
     *
     * @param grade The Grade object to add.
     * @throws SQLException if a database access error occurs.
     */
    void addGrade(Grade grade) throws SQLException;

    /**
     * Retrieves a grade by its ID.
     *
     * @param gradeId The ID of the grade to retrieve.
     * @return The Grade object if found, null otherwise.
     * @throws SQLException if a database access error occurs.
     */
    Grade getGradeById(int gradeId) throws SQLException;

    /**
     * Retrieves all grades for a specific student.
     *
     * @param studentId The ID of the student.
     * @return A list of Grade objects.
     * @throws SQLException if a database access error occurs.
     */
    List<Grade> getGradesByStudentId(int studentId) throws SQLException;

    /**
     * Retrieves all grades for a specific course.
     *
     * @param courseId The ID of the course.
     * @return A list of Grade objects.
     * @throws SQLException if a database access error occurs.
     */
    List<Grade> getGradesByCourseId(int courseId) throws SQLException;

    /**
     * Retrieves all grades for a specific student in a specific course.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @return A list of Grade objects.
     * @throws SQLException if a database access error occurs.
     */
    List<Grade> getGradesByStudentAndCourse(int studentId, int courseId) throws SQLException;

    /**
     * Updates an existing grade in the database.
     *
     * @param grade The Grade object to update.
     * @throws SQLException if a database access error occurs.
     */
    void updateGrade(Grade grade) throws SQLException;

    /**
     * Deletes a grade from the database by its ID.
     *
     * @param gradeId The ID of the grade to delete.
     * @throws SQLException if a database access error occurs.
     */
    void deleteGrade(int gradeId) throws SQLException;
}