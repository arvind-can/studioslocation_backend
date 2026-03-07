package com.arvindcan.studioslocation_backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

  @Value("${jwt.secret}")
  private String secretKey;

  public String generateToken(String username) {
    // Token attributes
    long currentTime = System.currentTimeMillis();
    Date issuedDate = new Date(currentTime);
    Date expiryDate = new Date(currentTime + 60 * 60 * 10);
    Map<String, Object> claims = new HashMap<>();

    // Build Token
    String token =
        Jwts.builder()
            .claims()
            .add(claims)
            .subject(username)
            .issuedAt(issuedDate)
            .expiration(expiryDate)
            .and()
            .signWith(getKey())
            .compact();

    return token;
  }

  private Key getKey() {
    byte[] secretKeyInBites = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(secretKeyInBites);
  }
}
