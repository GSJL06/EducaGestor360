package com.educagestor360.dao.impl;

import com.educagestor360.dao.GradeDAO;
import com.educagestor360.model.Grade;
import com.educagestor360.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GradeDAO interface.
 * Handles database operations for Grade entities.
 */
public class GradeDAOImpl implements GradeDAO {

    private static final String INSERT_GRADE_SQL = "INSERT INTO grades (student_id, course_id, score, comments) VALUES (?, ?, ?, ?)";
    private static final String SELECT_GRADE_BY_ID_SQL = "SELECT grade_id, student_id, course_id, score, comments FROM grades WHERE grade_id = ?";
    private static final String SELECT_GRADES_BY_STUDENT_ID_SQL = "SELECT grade_id, student_id, course_id, score, comments FROM grades WHERE student_id = ?";
    private static final String SELECT_GRADES_BY_COURSE_ID_SQL = "SELECT grade_id, student_id, course_id, score, comments FROM grades WHERE course_id = ?";
    private static final String SELECT_GRADES_BY_STUDENT_AND_COURSE_SQL = "SELECT grade_id, student_id, course_id, score, comments FROM grades WHERE student_id = ? AND course_id = ?";
    private static final String UPDATE_GRADE_SQL = "UPDATE grades SET student_id = ?, course_id = ?, score = ?, comments = ? WHERE grade_id = ?";
    private static final String DELETE_GRADE_SQL = "DELETE FROM grades WHERE grade_id = ?";

    /**
     * Adds a new grade to the database.
     * Retrieves the generated grade_id and sets it in the Grade object.
     *
     * @param grade The Grade object to add.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void addGrade(Grade grade) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRADE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, grade.getStudentId());
            preparedStatement.setInt(2, grade.getCourseId());
            preparedStatement.setDouble(3, grade.getScore());
            preparedStatement.setString(4, grade.getComments());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    grade.setGradeId(generatedKeys.getInt(1));
                }
            }
        }
    }

    /**
     * Retrieves a grade by its ID.
     *
     * @param gradeId The ID of the grade to retrieve.
     * @return The Grade object if found, null otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public Grade getGradeById(int gradeId) throws SQLException {
        Grade grade = null;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADE_BY_ID_SQL)) {
            preparedStatement.setInt(1, gradeId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    grade = new Grade();
                    grade.setGradeId(rs.getInt("grade_id"));
                    grade.setStudentId(rs.getInt("student_id"));
                    grade.setCourseId(rs.getInt("course_id"));
                    grade.setScore(rs.getDouble("score"));
                    grade.setComments(rs.getString("comments"));
                }
            }
        }
        return grade;
    }

    /**
     * Retrieves all grades for a specific student.
     *
     * @param studentId The ID of the student.
     * @return A list of Grade objects.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Grade> getGradesByStudentId(int studentId) throws SQLException {
        List<Grade> grades = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADES_BY_STUDENT_ID_SQL)) {
            preparedStatement.setInt(1, studentId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Grade grade = new Grade();
                    grade.setGradeId(rs.getInt("grade_id"));
                    grade.setStudentId(rs.getInt("student_id"));
                    grade.setCourseId(rs.getInt("course_id"));
                    grade.setScore(rs.getDouble("score"));
                    grade.setComments(rs.getString("comments"));
                    grades.add(grade);
                }
            }
        }
        return grades;
    }

    /**
     * Retrieves all grades for a specific course.
     *
     * @param courseId The ID of the course.
     * @return A list of Grade objects.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Grade> getGradesByCourseId(int courseId) throws SQLException {
        List<Grade> grades = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADES_BY_COURSE_ID_SQL)) {
            preparedStatement.setInt(1, courseId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Grade grade = new Grade();
                    grade.setGradeId(rs.getInt("grade_id"));
                    grade.setStudentId(rs.getInt("student_id"));
                    grade.setCourseId(rs.getInt("course_id"));
                    grade.setScore(rs.getDouble("score"));
                    grade.setComments(rs.getString("comments"));
                    grades.add(grade);
                }
            }
        }
        return grades;
    }

    /**
     * Retrieves all grades for a specific student in a specific course.
     *
     * @param studentId The ID of the student.
     * @param courseId The ID of the course.
     * @return A list of Grade objects.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Grade> getGradesByStudentAndCourse(int studentId, int courseId) throws SQLException {
        List<Grade> grades = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADES_BY_STUDENT_AND_COURSE_SQL)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Grade grade = new Grade();
                    grade.setGradeId(rs.getInt("grade_id"));
                    grade.setStudentId(rs.getInt("student_id"));
                    grade.setCourseId(rs.getInt("course_id"));
                    grade.setScore(rs.getDouble("score"));
                    grade.setComments(rs.getString("comments"));
                    grades.add(grade);
                }
            }
        }
        return grades;
    }

    /**
     * Updates an existing grade in the database.
     *
     * @param grade The Grade object to update.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void updateGrade(Grade grade) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GRADE_SQL)) {
            preparedStatement.setInt(1, grade.getStudentId());
            preparedStatement.setInt(2, grade.getCourseId());
            preparedStatement.setDouble(3, grade.getScore());
            preparedStatement.setString(4, grade.getComments());
            preparedStatement.setInt(5, grade.getGradeId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Deletes a grade from the database by its ID.
     *
     * @param gradeId The ID of the grade to delete.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void deleteGrade(int gradeId) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GRADE_SQL)) {
            preparedStatement.setInt(1, gradeId);
            preparedStatement.executeUpdate();
        }
    }
}