package com.spriegade.passwordmanagerbackend.API.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "userdata")
public class User {

    @Id
    private String email;
    @Column(name = "masterpassword")
    private String masterPassword;

    public User(String email, String masterPassword) {
        this.masterPassword = masterPassword;
        this.email = email;
    }

    public User() {

    }

}
