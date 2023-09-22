package com.spriegade.passwordmanagerbackend.api.controller;

import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.UserRepository;
import com.spriegade.passwordmanagerbackend.api.services.UserService;
import com.spriegade.passwordmanagerbackend.utils.SessionTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

import static com.spriegade.passwordmanagerbackend.api.constants.UserApiConstants.*;

@RestController
@RequestMapping(USER_API)
@RequiredArgsConstructor
public class UserController {
    //Controller:
    //    Role: Controllers are responsible for handling incoming HTTP requests, processing them, and returning appropriate HTTP responses. They act as the entry point to your API.
    //    Functionality: Controllers define the API endpoints (HTTP URLs) and map them to specific methods or actions. These methods process the incoming requests, validate data, invoke services or repositories to perform database operations, and then return responses to clients.
    //    Example: A UserController might have methods for handling user-related HTTP requests such as creating users, retrieving user profiles, or updating user information.
    private final UserRepository userRepository;
    private final UserService userService;
    private final SessionTokenGenerator sessionTokenGenerator;

    @GetMapping(USER_HELLO_WORLD)
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping(IS_USER_EXISTING)
    public ResponseEntity<Boolean> getUserExisting(@RequestParam String email) {
        User user = userRepository.findByUsername(email);
        if (user != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(VALIDATE_MASTER_PASSWORD)
    public ResponseEntity<String> validateMasterPassword(@RequestParam String email, String masterPassword) {
        User user = userRepository.findByUsername(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (userService.isSessionDate3DaysOld(user.getUsername())){
            user.setSessionToken(null);
            user.setSessionTokenCreated(null);
            userRepository.save(user);
        }
        if (!masterPassword.equals(user.getMasterPassword())){
            return ResponseEntity.notFound().build();
        }
        if (user.getSessionToken() == null){
            String sessionToken = sessionTokenGenerator.generateSessionToken();
            Calendar calendar = Calendar.getInstance();
            user.setSessionTokenCreated(calendar.getTime());
            user.setSessionToken(sessionToken);
            userRepository.save(user);

            return ResponseEntity.ok(sessionToken);
        }
        return ResponseEntity.ok(user.getSessionToken());
    }

    //testurL: http://localhost:8080/userApi/validatePassword?email=timothyspriegade@outlook.de&masterPassword=e0f661fbbaebfc70bde48c2b6bb88c08c54cd2c49f5c848e92cdc33554d6f37f
    //TODO: muss Create User Neu schreiben
    @PostMapping(CREATE_NEW_USER)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            userService.createUser(user.getUsername(), user.getMasterPassword());
            return ResponseEntity.ok("User created successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

