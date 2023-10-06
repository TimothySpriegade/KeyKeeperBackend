package com.spriegade.passwordmanagerbackend.api.services;

import com.spriegade.passwordmanagerbackend.api.entities.Password;
import com.spriegade.passwordmanagerbackend.api.entities.User;
import com.spriegade.passwordmanagerbackend.api.repositories.PasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final PasswordRepository passwordRepository;

    public Password createPassword(String name, String password, String url, User user) {
        Password newPassword = new Password();
        newPassword.setName(name);
        newPassword.setPassword(password);
        newPassword.setUrl(url);
        newPassword.setUser(user);

        return passwordRepository.save(newPassword);
    }


}
