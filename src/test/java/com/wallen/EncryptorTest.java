package com.wallen;

import org.jasypt.encryption.StringEncryptor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Wallen
 * @date: 2022/3/10 11:38
 */
@SpringBootTest
class EncryptorTest {
    @Autowired
    private StringEncryptor stringEncryptor;

   @Test
    void contextLoads() {
        //加密工具
        String account = stringEncryptor.encrypt("root");
        String password = stringEncryptor.encrypt("12345678");
        System.out.println(account);
        System.out.println(password);
    }
}

