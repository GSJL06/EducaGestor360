package com.educagestor360.dao;

import com.educagestor360.model.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) interface for User entity.
 * Defines standard operations to be performed on User objects.
 */
public interface UserDAO {

    /**
     * Adds a new user to the database.
     *
     * @param user The User object to be added.
     * @throws SQLException If a database access error occurs.
     */
    void addUser(User user) throws SQLException;

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object if found, null otherwise.
     * @throws SQLException If a database access error occurs.
     */
    User getUserById(int userId) throws SQLException;

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The User object if found, null otherwise.
     * @throws SQLException If a database access error occurs.
     */
    User getUserByEmail(String email) throws SQLException;

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all User objects.
     * @throws SQLException If a database access error occurs.
     */
    List<User> getAllUsers() throws SQLException;

    /**
     * Updates an existing user's information in the database.
     *
     * @param user The User object with updated information.
     * @throws SQLException If a database access error occurs.
     */
    void updateUser(User user) throws SQLException;

    /**
     * Deletes a user from the database by their ID.
     *
     * @param userId The ID of the user to delete.
     * @throws SQLException If a database access error occurs.
     */
    void deleteUser(int userId) throws SQLException;
}