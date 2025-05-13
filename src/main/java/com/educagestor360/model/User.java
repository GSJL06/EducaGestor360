package com.educagestor360.model;

/**
 * Represents a User in the EducaGestor360 system.
 * Can be a student, teacher, or administrator.
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password; // In a real application, this should be securely hashed.
    private String role; // e.g., "STUDENT", "TEACHER", "ADMIN"

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor with all fields.
     *
     * @param userId    The unique ID of the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param password  The password for the user account.
     * @param role      The role assigned to the user.
     */
    public User(int userId, String firstName, String lastName, String email, String password, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
               "userId=" + userId +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               '}';
    }
}