package com.educagestor360.demo;

import com.educagestor360.model.User;
import com.educagestor360.model.Course;
import com.educagestor360.model.Grade;
import com.educagestor360.dao.impl.UserDAOImpl;
import com.educagestor360.dao.impl.CourseDAOImpl;
import com.educagestor360.dao.impl.GradeDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class EducaGestorDemo {

    public static void main(String[] args) {
        // Instantiate DAOs
        UserDAOImpl userDAO = new UserDAOImpl();
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        GradeDAOImpl gradeDAO = new GradeDAOImpl();

        try {
            // --- User CRUD Operations ---
            System.out.println("--- User CRUD Operations ---");

            // Create User
            User newUser = new User(0, "John Doe", "john.doe@example.com", "student");
            userDAO.addUser(newUser);
            System.out.println("User added successfully: " + newUser.getName());

            // Retrieve User by Email
            User retrievedUser = userDAO.getUserByEmail("john.doe@example.com");
            if (retrievedUser != null) {
                System.out.println("Retrieved user by email: " + retrievedUser.getName());
                // Update User
                retrievedUser.setName("Jane Doe");
                userDAO.updateUser(retrievedUser);
                System.out.println("User updated successfully to: " + retrievedUser.getName());

                // Retrieve User by ID (after update)
                User updatedUser = userDAO.getUserById(retrievedUser.getUserId());
                if (updatedUser != null) {
                    System.out.println("Retrieved updated user by ID: " + updatedUser.getName());
                }
            } else {
                System.out.println("User not found after creation.");
            }

            // --- Course CRUD Operations ---
            System.out.println("\n--- Course CRUD Operations ---");

            // Create Course
            Course newCourse = new Course(0, "Introduction to Java", 1); // Assuming teacherId 1 exists
            courseDAO.addCourse(newCourse);
            System.out.println("Course added successfully: " + newCourse.getCourseName());

            // Retrieve all Courses
            List<Course> allCourses = courseDAO.getAllCourses();
            System.out.println("All courses:");
            for (Course course : allCourses) {
                System.out.println("- " + course.getCourseName());
            }

            // Retrieve Course by ID
            Course retrievedCourse = courseDAO.getCourseById(newCourse.getCourseId());
            if (retrievedCourse != null) {
                System.out.println("Retrieved course by ID: " + retrievedCourse.getCourseName());
                // Update Course
                retrievedCourse.setCourseName("Advanced Java");
                courseDAO.updateCourse(retrievedCourse);
                System.out.println("Course updated successfully to: " + retrievedCourse.getCourseName());
            } else {
                System.out.println("Course not found after creation.");
            }

            // --- Grade CRUD Operations ---
            System.out.println("\n--- Grade CRUD Operations ---");

            // Create Grade
            // Assuming studentId and courseId exist (e.g., from the users/courses created above or existing data)
            // For demonstration, let's use the retrievedUser's ID and the updatedCourse's ID
            if (retrievedUser != null && retrievedCourse != null) {
                 Grade newGrade = new Grade(0, retrievedUser.getUserId(), retrievedCourse.getCourseId(), 95.5);
                 gradeDAO.addGrade(newGrade);
                 System.out.println("Grade added successfully for student " + retrievedUser.getName() + " in course " + retrievedCourse.getCourseName());

                 // Retrieve Grades by Student ID
                 List<Grade> studentGrades = gradeDAO.getGradesByStudentId(retrievedUser.getUserId());
                 System.out.println("Grades for student " + retrievedUser.getName() + ":");
                 for (Grade grade : studentGrades) {
                     System.out.println("- Grade ID: " + grade.getGradeId() + ", Score: " + grade.getScore());
                 }

                 // Retrieve Grade by ID
                 Grade retrievedGrade = gradeDAO.getGradeById(newGrade.getGradeId());
                 if (retrievedGrade != null) {
                     System.out.println("Retrieved grade by ID: " + retrievedGrade.getGradeId());
                     // Update Grade
                     retrievedGrade.setScore(98.0);
                     gradeDAO.updateGrade(retrievedGrade);
                     System.out.println("Grade updated successfully to: " + retrievedGrade.getScore());
                 } else {
                     System.out.println("Grade not found after creation.");
                 }

                 // Delete Grade
                 if (retrievedGrade != null) {
                     gradeDAO.deleteGrade(retrievedGrade.getGradeId());
                     System.out.println("Grade deleted successfully: " + retrievedGrade.getGradeId());
                 }
            } else {
                System.out.println("Cannot demonstrate Grade CRUD as User or Course was not found.");
            }


            // --- Deletion Operations (Optional, uncomment to test deletion) ---
            /*
            System.out.println("\n--- Deletion Operations ---");
            if (retrievedUser != null) {
                userDAO.deleteUser(retrievedUser.getUserId());
                System.out.println("User deleted successfully: " + retrievedUser.getUserId());
            }
            if (retrievedCourse != null) {
                 courseDAO.deleteCourse(retrievedCourse.getCourseId());
                 System.out.println("Course deleted successfully: " + retrievedCourse.getCourseId());
            }
            */

        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}