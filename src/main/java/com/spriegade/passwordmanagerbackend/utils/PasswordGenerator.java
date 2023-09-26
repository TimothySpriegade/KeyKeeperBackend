package com.spriegade.passwordmanagerbackend.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PasswordGenerator {
    public String generatePassword(int length, boolean hasNumbers, boolean hasSpecialCharacters) {
        String password = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*_=+-/.?<>)";

        String combinedChars = alphabet;
        if (hasNumbers) {
            combinedChars += numbers;
        }
        if (hasSpecialCharacters) {
            combinedChars += specialCharacters;
        }

        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * combinedChars.length());
            password += combinedChars.charAt(rand);
        }

        return password;
    }
}
