package com.logone.backend.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.logone.backend.Model.UserModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
  private static final String SECRET = "729ACF3BB3CB2B209FAB8B1D9397724FB54C4F95B9473C9EEAE6123C7C6A907AE625A601A741DC9B659402441AFE5DEE0DDACBD842AE63097B6C396AA37D31DB";
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
