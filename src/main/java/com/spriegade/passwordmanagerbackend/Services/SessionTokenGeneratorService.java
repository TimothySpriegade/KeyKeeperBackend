package com.spriegade.passwordmanagerbackend.Services;

import java.util.Random;

public class SessionTokenGeneratorService {

    public SessionTokenGeneratorService() {
    }

    public String generateSessionToken() {
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
        char[] sessionTokenChars = new char[20];
        for (int i = 0; i <= (sessionTokenChars.length - 1); i++) {
            sessionTokenChars[i] = alphabet.charAt(random.nextInt(alphabet.length()));
        }
        String sessionTokenToHash = new String(sessionTokenChars);
        HashSHA256Service hashService = new HashSHA256Service();
        return  hashService.hashStringSHA256(sessionTokenToHash);
    }
}
