package com.spriegade.passwordmanagerbackend.api.controller;

import com.spriegade.passwordmanagerbackend.utils.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/passwordGenerationApi")
@RequiredArgsConstructor
@CrossOrigin
public class PasswordGenerationController {

    private final PasswordGenerator passwordGenerator;

    @GetMapping("/getGeneratedPassword")
    public ResponseEntity<Map<String, String>> generatePassword() {
        String generatedPassword = passwordGenerator.generatePassword(25, true, true);
        Map<String, String> response = new HashMap<>();
        response.put("generatedPassword", generatedPassword);
        return ResponseEntity.ok(response);
    }

}
