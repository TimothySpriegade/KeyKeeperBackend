package com.spriegade.passwordmanagerbackend.api.repositories;

import com.spriegade.passwordmanagerbackend.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Role: Repositories are responsible for database interactions. They abstract away the low-level database operations and provide a higher-level interface to interact with the database.
 * Functionality: Repositories typically include methods for common database operations like creating, reading, updating, and deleting records. They also allow you to define custom queries using method names or SQL queries.
 * Example: A UserRepository would provide methods for creating, finding, updating, and deleting user records in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserBySessionToken(String sessionToken);
}