package com.educagestor360.dao;

import com.educagestor360.model.Course;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) interface for Course entities.
 * Defines standard operations to be performed on Course objects.
 */
public interface CourseDAO {

    /**
     * Adds a new course to the database.
     *
     * @param course The Course object to add.
     * @throws SQLException if a database access error occurs.
     */
    void addCourse(Course course) throws SQLException;

    /**
     * Retrieves a course by its ID.
     *
     * @param courseId The ID of the course to retrieve.
     * @return The Course object if found, null otherwise.
     * @throws SQLException if a database access error occurs.
     */
    Course getCourseById(int courseId) throws SQLException;

    /**
     * Retrieves all courses from the database.
     *
     * @return A list of all Course objects.
     * @throws SQLException if a database access error occurs.
     */
    List<Course> getAllCourses() throws SQLException;

    /**
     * Retrieves all courses taught by a specific teacher.
     *
     * @param teacherId The ID of the teacher.
     * @return A list of Course objects taught by the specified teacher.
     * @throws SQLException if a database access error occurs.
     */
    List<Course> getCoursesByTeacherId(int teacherId) throws SQLException;

    /**
     * Updates an existing course in the database.
     *
     * @param course The Course object with updated information.
     * @throws SQLException if a database access error occurs.
     */
    void updateCourse(Course course) throws SQLException;

    /**
     * Deletes a course from the database by its ID.
     *
     * @param courseId The ID of the course to delete.
     * @throws SQLException if a database access error occurs.
     */
    void deleteCourse(int courseId) throws SQLException;
}