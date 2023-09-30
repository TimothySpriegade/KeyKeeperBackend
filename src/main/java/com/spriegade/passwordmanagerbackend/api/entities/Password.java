package com.spriegade.passwordmanagerbackend.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "passwords")
public class Password {

    @Id
    private String name;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private String url;

    @ManyToOne
    @JsonIgnore
    private User user;
}
