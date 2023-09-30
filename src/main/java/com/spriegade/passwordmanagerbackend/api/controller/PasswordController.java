package com.spriegade.passwordmanagerbackend.api.controller;

import com.spriegade.passwordmanagerbackend.api.entities.Password;
import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.PasswordRepository;
import com.spriegade.passwordmanagerbackend.api.repositories.UserRepository;
import com.spriegade.passwordmanagerbackend.api.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.notFound().build();
        }

        if (passwordRepository.findPasswordByName(body.get("name")) != null) {
            return ResponseEntity.notFound().build();
        }

        Password newPassword = passwordService.savePassword(body.get("name"),
                body.get("password"),
                body.get("username"),
                body.get("url"),
                body.get("notes"),
                user);

        user.getPasswords().add(newPassword);
        passwordRepository.save(newPassword);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
