package com.spriegade.passwordmanagerbackend.api.controller;

import com.spriegade.passwordmanagerbackend.utils.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passwordGenerationApi")
@RequiredArgsConstructor
@CrossOrigin
public class PasswordGenerationController {

    private final PasswordGenerator passwordGenerator;

    @GetMapping("/getGeneratedPassword")
    public ResponseEntity<String> generatePassword() {
        String generatedPassword = passwordGenerator.generatePassword(25, true, true);
        return ResponseEntity.ok(generatedPassword);
    }

}
