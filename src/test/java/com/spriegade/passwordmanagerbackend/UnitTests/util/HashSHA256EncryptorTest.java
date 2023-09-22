package com.spriegade.passwordmanagerbackend.UnitTests.util;

import com.spriegade.passwordmanagerbackend.utils.HashSHA256Encryptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HashSHA256EncryptorTest {
    private HashSHA256Encryptor hashEncryptor;

    @BeforeAll
    void setUp(){
        hashEncryptor = new HashSHA256Encryptor();
    }

    @Test
    void testHashStringSHA256(){
        // given
        String input = "Hello, World!";
        String expectedHash = "dffd6021bb2bd5b0af676290809ec3a53191dd81c7f70a4b28688a362182986f";

        // when
        String actualHash = hashEncryptor.hashStringSHA256(input);

        // then
        assertThat(actualHash).isEqualTo(expectedHash);
    }
}
