package com.spriegade.passwordmanagerbackend.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "userdata")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String username;

    @Column
    private String masterPassword;

    @Column
    private String sessionToken;

    @Column
    @Temporal(TemporalType.DATE)
    private Date sessionTokenCreated;

    @Column
    @OneToMany
    private List<Password> passwords;
}
