package com.spriegade.passwordmanagerbackend.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class SessionTokenGenerator {
    private final HashSHA256Encryptor hashEncryptor;

    public String generateSessionToken() {
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        char[] sessionTokenChars = new char[20];
        for (int i = 0; i <= (sessionTokenChars.length - 1); i++) {
            sessionTokenChars[i] = alphabet.charAt(random.nextInt(alphabet.length()));
        }
        String sessionTokenToHash = new String(sessionTokenChars);
        return hashEncryptor.hashStringSHA256(sessionTokenToHash);
    }
}