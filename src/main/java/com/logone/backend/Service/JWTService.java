package com.logone.backend.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.logone.backend.Model.UserModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
  @Value("${app.secret}")
  private String SECRET;
  private static final Long Validitiy = TimeUnit.HOURS.toMillis(12);

  public String generateToken(UserModel user) {
    Map<String, String> claims = new HashMap<>();
    claims.put("iss", "logone");
    return Jwts.builder().claims(claims).subject(user.getEmail()).issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(Instant.now().plusMillis(Validitiy))).signWith(generateKey()).compact();
  }

  private SecretKey generateKey() {
    byte[] decodedKey = Base64.getDecoder().decode(SECRET);
    return Keys.hmacShaKeyFor(decodedKey);
  }
}
