package com.spriegade.passwordmanagerbackend.UnitTests.Services;

import com.spriegade.passwordmanagerbackend.Services.HashSHA256Service;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashSHA256ServiceTest {

    @Test
    public void testHashStringSHA256(){
        String input = "Hello, World!";
        String expectedHash = "dffd6021bb2bd5b0af676290809ec3a53191dd81c7f70a4b28688a362182986f";

        HashSHA256Service hashService = new HashSHA256Service();
        String actualHash = hashService.hashStringSHA256(input);

        assertEquals(expectedHash, actualHash);
    }
}
