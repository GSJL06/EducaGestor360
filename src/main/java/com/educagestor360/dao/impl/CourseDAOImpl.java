package com.educagestor360.dao.impl;

import com.educagestor360.dao.CourseDAO;
import com.educagestor360.model.Course;
import com.educagestor360.util.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the CourseDAO interface.
 * Handles database operations for Course entities.
 */
public class CourseDAOImpl implements CourseDAO {

    private static final String INSERT_COURSE_SQL = "INSERT INTO courses (course_name, description, teacher_id, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_COURSE_BY_ID_SQL = "SELECT course_id, course_name, description, teacher_id, start_date, end_date FROM courses WHERE course_id = ?";
    private static final String SELECT_ALL_COURSES_SQL = "SELECT course_id, course_name, description, teacher_id, start_date, end_date FROM courses";
    private static final String SELECT_COURSES_BY_TEACHER_ID_SQL = "SELECT course_id, course_name, description, teacher_id, start_date, end_date FROM courses WHERE teacher_id = ?";
    private static final String UPDATE_COURSE_SQL = "UPDATE courses SET course_name = ?, description = ?, teacher_id = ?, start_date = ?, end_date = ? WHERE course_id = ?";
    private static final String DELETE_COURSE_SQL = "DELETE FROM courses WHERE course_id = ?";

    /**
     * Adds a new course to the database.
     * Retrieves the generated course_id and sets it in the Course object.
     *
     * @param course The Course object to add.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void addCourse(Course course) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COURSE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getTeacherId());
            preparedStatement.setDate(4, course.getStartDate() != null ? new java.sql.Date(course.getStartDate().getTime()) : null);
            preparedStatement.setDate(5, course.getEndDate() != null ? new java.sql.Date(course.getEndDate().getTime()) : null);
            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setCourseId(generatedKeys.getInt(1));
            }
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException e) { /* ignored */ }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* ignored */ }
            if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
        }
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param courseId The ID of the course to retrieve.
     * @return The Course object if found, null otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public Course getCourseById(int courseId) throws SQLException {
        Course course = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_COURSE_BY_ID_SQL);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course = mapResultSetToCourse(resultSet);
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { /* ignored */ }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* ignored */ }
            if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
        }
        return course;
    }

    /**
     * Retrieves all courses from the database.
     *
     * @return A list of all Course objects.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_COURSES_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(mapResultSetToCourse(resultSet));
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { /* ignored */ }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* ignored */ }
            if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
        }
        return courses;
    }

    /**
     * Retrieves all courses taught by a specific teacher.
     *
     * @param teacherId The ID of the teacher.
     * @return A list of Course objects taught by the specified teacher.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Course> getCoursesByTeacherId(int teacherId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_TEACHER_ID_SQL);
            preparedStatement.setInt(1, teacherId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(mapResultSetToCourse(resultSet));
            }
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { /* ignored */ }
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* ignored */ }
            if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
        }
        return courses;
    }

    /**
     * Updates an existing course in the database.
     *
     * @param course The Course object with updated information.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void updateCourse(Course course) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COURSE_SQL);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getTeacherId());
            preparedStatement.setDate(4, course.getStartDate() != null ? new java.sql.Date(course.getStartDate().getTime()) : null);
            preparedStatement.setDate(5, course.getEndDate() != null ? new java.sql.Date(course.getEndDate().getTime()) : null);
            preparedStatement.setInt(6, course.getCourseId());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* ignored */ }
            if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
        }
    }

    /**
     * Deletes a course from the database by its ID.
     *
     * @param courseId The ID of the course to delete.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void deleteCourse(int courseId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_COURSE_SQL);
            preparedStatement.setInt(1, courseId);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) { /* ignored */ }
            if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
        }
    }

    /**
     * Helper method to map a ResultSet row to a Course object.
     * @param resultSet The ResultSet to map.
     * @return A Course object.
     * @throws SQLException if a database access error occurs.
     */
    private Course mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getInt("course_id"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setDescription(resultSet.getString("description"));
        course.setTeacherId(resultSet.getInt("teacher_id"));
        course.setStartDate(resultSet.getDate("start_date"));
        course.setEndDate(resultSet.getDate("end_date"));
        return course;
    }
}