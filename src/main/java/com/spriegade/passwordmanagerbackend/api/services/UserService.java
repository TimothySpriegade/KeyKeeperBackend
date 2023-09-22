package com.spriegade.passwordmanagerbackend.api.services;

import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserService {
    //    Role: Services contain the business logic of your application. They encapsulate and manage complex operations that involve multiple entities or repositories.
    //    Functionality: Services coordinate interactions between controllers and repositories. They perform operations that require application-specific logic, data validation, and transaction management. Services help keep controllers lean and focused on request handling.
    //    Example: A UserService might contain methods for registering users, verifying passwords, and managing user roles, encapsulating the logic related to user management.
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createUser(String email, String masterPassword) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setMasterPassword(masterPassword);
        userRepository.save(newUser);
    }

    public boolean isSessionDate3DaysOld(String email){
        User user = userRepository.findByEmail(email);
        if (user != null && user.getSessionTokenCreated() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -3);
            Date sessionTokenDeadline = calendar.getTime();
            return user.getSessionTokenCreated().before(sessionTokenDeadline);
        }
        return false;
    }
}
