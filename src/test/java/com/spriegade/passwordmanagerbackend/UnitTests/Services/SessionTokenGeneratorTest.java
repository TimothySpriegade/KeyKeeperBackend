package com.spriegade.passwordmanagerbackend.UnitTests.Services;

import com.spriegade.passwordmanagerbackend.Services.SessionTokenGeneratorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SessionTokenGeneratorTest {
    @Test
    public void testGenerateSessionToken() {
        SessionTokenGeneratorService generator = new SessionTokenGeneratorService();
        String sessionToken = generator.generateSessionToken();

        assertNotNull(sessionToken);
        assertEquals(64, sessionToken.length());
    }
}
