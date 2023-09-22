package com.spriegade.passwordmanagerbackend.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "userdata")
public class User {

    @Id
    private String email;
    @Column(name = "masterpassword")
    private String masterPassword;
    @Column
    private String sessionToken;
    @Column
    @Temporal(TemporalType.DATE)
    private Date sessionTokenCreated;

    public User(String email, String masterPassword, String sessionToken, Date sessionTokenCreated) {
        this.masterPassword = masterPassword;
        this.email = email;
        this.sessionToken = sessionToken;
        this.sessionTokenCreated = sessionTokenCreated;
    }

    public User() {

    }

}
