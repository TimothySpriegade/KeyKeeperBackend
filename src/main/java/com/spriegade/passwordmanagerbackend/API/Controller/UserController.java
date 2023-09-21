package com.spriegade.passwordmanagerbackend.API.Controller;

import com.spriegade.passwordmanagerbackend.API.ApiServices.UserService;
import com.spriegade.passwordmanagerbackend.API.Entities.User;
import com.spriegade.passwordmanagerbackend.API.Repositories.UserRepository;
import com.spriegade.passwordmanagerbackend.Services.SessionTokenGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.spriegade.passwordmanagerbackend.API.constants.UserApiConstants.*;

@RestController
@RequestMapping(USER_API)
public class UserController {
    //Controller:
    //    Role: Controllers are responsible for handling incoming HTTP requests, processing them, and returning appropriate HTTP responses. They act as the entry point to your API.
    //    Functionality: Controllers define the API endpoints (HTTP URLs) and map them to specific methods or actions. These methods process the incoming requests, validate data, invoke services or repositories to perform database operations, and then return responses to clients.
    //    Example: A UserController might have methods for handling user-related HTTP requests such as creating users, retrieving user profiles, or updating user information.
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(USER_HELLO_WORLD)
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping(IS_USER_EXISTING)
    public ResponseEntity<Boolean> getUserExisting(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(VALIDATE_MASTER_PASSWORD)
    public ResponseEntity<String> validateMasterPassword(@RequestParam String email, String masterPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (!masterPassword.equals(user.getMasterPassword())){
            return ResponseEntity.notFound().build();
        }
        if (user.getSessionToken() == null){
            SessionTokenGeneratorService sessionTokenGenerator = new SessionTokenGeneratorService();
            String sessionToken = sessionTokenGenerator.generateSessionToken();
            user.setSessionToken(sessionToken);
            userRepository.save(user);
            return ResponseEntity.ok(sessionToken);
        }
        return ResponseEntity.ok(user.getSessionToken());
    }

    //testurL: http://localhost:8080/userApi/validatePassword?email=timothyspriegade@outlook.de&masterPassword=e0f661fbbaebfc70bde48c2b6bb88c08c54cd2c49f5c848e92cdc33554d6f37f

    @PostMapping(CREATE_NEW_USER)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            userService.createUser(user.getEmail(), user.getMasterPassword());
            return ResponseEntity.ok("User created successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}

