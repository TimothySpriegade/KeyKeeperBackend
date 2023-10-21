package com.spriegade.passwordmanagerbackend.api.controller;

import com.spriegade.passwordmanagerbackend.api.entities.Password;
import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.PasswordRepository;
import com.spriegade.passwordmanagerbackend.api.repositories.UserRepository;
import com.spriegade.passwordmanagerbackend.api.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/passwordApi")
@RequiredArgsConstructor
@CrossOrigin
public class PasswordController {

    private final PasswordRepository passwordRepository;
    private final UserRepository userRepository;
    private final PasswordService passwordService;


    @PostMapping("/postPassword")
    public ResponseEntity<User> postPassword(@RequestBody Map<String, String> body) {
        User user = userRepository.findUserByUsername(body.get("userUsername"));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        if (passwordRepository.findPasswordByName(body.get("name")) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password already existing");
        }
        Password newPassword = passwordService.createPassword(body.get("name"), body.get("password"),
                body.get("url"), body.get("username") , user);


        user.getPasswords().add(newPassword);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
