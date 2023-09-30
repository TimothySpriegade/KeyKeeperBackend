package com.spriegade.passwordmanagerbackend.api.controller;

import com.spriegade.passwordmanagerbackend.api.entities.Password;
import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.PasswordRepository;
import com.spriegade.passwordmanagerbackend.api.repositories.UserRepository;
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


    @PostMapping("/postPassword")
    public ResponseEntity<User> postPassword(@RequestBody Map<String, String> body) {
        User user = userRepository.findUserByUsername(body.get("userUsername"));
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (passwordRepository.findPasswordByName(body.get("name")) != null) {
            return ResponseEntity.notFound().build();
        }

        Password newPassword = new Password();
        newPassword.setName(body.get("name"));
        newPassword.setUsername(body.get("username"));
        newPassword.setPassword(body.get("password"));
        newPassword.setUrl(body.get("url"));
        newPassword.setNotes(body.get("notes"));
        newPassword.setUser(user);

        passwordRepository.save(newPassword);

        user.getPasswords().add(newPassword);
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
