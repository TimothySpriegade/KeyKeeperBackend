package com.spriegade.passwordmanagerbackend.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

@Data
@Entity
@NoArgsConstructor
public class Password {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String password;
}
