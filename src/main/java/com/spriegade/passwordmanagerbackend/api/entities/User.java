package com.spriegade.passwordmanagerbackend.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "userdata")
public class User {

    @Id
    private String username;

    @Column
    private String masterPassword;

    @Column
    private String sessionToken;

    @Column
    @Temporal(TemporalType.DATE)
    private Date sessionTokenCreated;

    @Column
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Password> passwords;
}
