package com.paginationpractice.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

/**
 *  This class JwtTokenUtil is responsible to create, extractUsername and validateToken.
 *  It is very important to realize that we are using the Key in order to encrypt the String ,and
 *  now we can use TimeUnit instead of writting hardcode milliseconds
 */
@Component
public class JwtTokenUtil {

  private static final String SECRET = "bXlzZWN1cmVteXNlY3VyZW15c2VjdXJlbXlzZWN1cmU=";
  // Example secret key, should be stored securely
  private final static Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

  private final static long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(1); // Valid for 1 hour

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(SECRET_KEY)
          .build().parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
