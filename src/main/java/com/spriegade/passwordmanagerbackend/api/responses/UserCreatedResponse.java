package com.spriegade.passwordmanagerbackend.api.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class UserCreatedResponse {
    private boolean isUserCreated;
}
