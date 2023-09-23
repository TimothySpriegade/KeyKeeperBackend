package com.spriegade.passwordmanagerbackend.api.controller;

import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.UserRepository;
import com.spriegade.passwordmanagerbackend.api.responses.SessionTokenResponse;
import com.spriegade.passwordmanagerbackend.api.services.UserService;
import com.spriegade.passwordmanagerbackend.utils.SessionTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Map;

/**
 * Controller:
 * Role: Controllers are responsible for handling incoming HTTP requests, processing them, and returning appropriate HTTP responses. They act as the entry point to your API.
 * Functionality: Controllers define the API endpoints (HTTP URLs) and map them to specific methods or actions. These methods process the incoming requests, validate data, invoke services or repositories to perform database operations, and then return responses to clients.
 * Example: A UserController might have methods for handling user-related HTTP requests such as creating users, retrieving user profiles, or updating user information.
 */

@RestController
@RequestMapping("userApi")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final SessionTokenGenerator sessionTokenGenerator;
    private final SessionTokenResponse sessionTokenResponse;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/getUser/validateUserExistence")
    public ResponseEntity<Boolean> getUserExisting(@RequestParam String username) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUser/validate")
    public ResponseEntity<SessionTokenResponse> validateUser(@RequestParam String username, String masterPassword) {
        User user = userRepository.findUserByUsername(username);

        if (user == null || !masterPassword.equals(user.getMasterPassword())) {
            return ResponseEntity.notFound().build();
        }

        if (userService.isSessionDate3DaysOld(user.getUsername())) {
            user.setSessionToken(null);
            user.setSessionTokenCreated(null);
            userRepository.save(user);
        }

        if (user.getSessionToken() == null) {
            String sessionToken = sessionTokenGenerator.generateSessionToken();
            Calendar calendar = Calendar.getInstance();
            user.setSessionTokenCreated(calendar.getTime());
            user.setSessionToken(sessionToken);
            userRepository.save(user);

            sessionTokenResponse.setSessionToken(sessionToken);
        }
        else {
            sessionTokenResponse.setSessionToken(user.getSessionToken());
        }
        return ResponseEntity.ok(sessionTokenResponse);
    }

    @PostMapping("/postUser")
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String masterPassword = requestBody.get("masterPassword");
        User existingUser = userRepository.findUserByUsername(username);
        if (existingUser == null) {
            userService.createUser(username, masterPassword);
            return ResponseEntity.ok("User created successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

