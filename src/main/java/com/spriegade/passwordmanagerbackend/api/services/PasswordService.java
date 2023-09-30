package com.spriegade.passwordmanagerbackend.api.services;

import com.spriegade.passwordmanagerbackend.api.entities.Password;
import com.spriegade.passwordmanagerbackend.api.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    public Password savePassword(String name, String password, String username, String url, String notes, User user) {
        Password toSavePassword = new Password();
        toSavePassword.setName(name);
        toSavePassword.setPassword(password);
        toSavePassword.setUsername(username);
        toSavePassword.setUrl(url);
        toSavePassword.setNotes(notes);
        toSavePassword.setUser(user);
        return toSavePassword;
    }
}
