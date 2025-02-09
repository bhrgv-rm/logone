package com.logone.backend;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.xml.bind.DatatypeConverter;

@SpringBootTest
public class JWTSecretMakerTest {
  @Test
  public static void main(String[] args) {
    SecretKey key = Jwts.SIG.HS512.key().build();
    String keyEncoded = DatatypeConverter.printHexBinary(key.getEncoded());
    System.out.println(keyEncoded);
  }
}
