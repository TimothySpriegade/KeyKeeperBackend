package com.spriegade.passwordmanagerbackend.Services;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSHA256Service {

    public HashSHA256Service() {
    }

    public String hashStringSHA256(String stringToHash) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");


            byte[] hashBytes = digest.digest(stringToHash.getBytes());


            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
