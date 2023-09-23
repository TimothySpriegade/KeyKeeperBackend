package com.spriegade.passwordmanagerbackend.api.services;

import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;

/**
 * Service:
 * Role: Services contain the business logic of your application. They encapsulate and manage complex operations that involve multiple entities or repositories.
 * Functionality: Services coordinate interactions between controllers and repositories. They perform operations that require application-specific logic, data validation, and transaction management. Services help keep controllers lean and focused on request handling.
 * Example: A UserService might contain methods for registering users, verifying passwords, and managing user roles, encapsulating the logic related to user management.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(String username, String masterPassword) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setMasterPassword(masterPassword);
        try {
            return userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already existing");
        }
    }

    public boolean isSessionDate3DaysOld(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user != null && user.getSessionTokenCreated() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -3);
            Date sessionTokenDeadline = calendar.getTime();
            return user.getSessionTokenCreated().before(sessionTokenDeadline);
        }
        return false;
    }
}
