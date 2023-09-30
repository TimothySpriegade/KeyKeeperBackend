package com.spriegade.passwordmanagerbackend.api.repositories;


import com.spriegade.passwordmanagerbackend.api.entities.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, String> {
    Password findPasswordByName(String name);
}
