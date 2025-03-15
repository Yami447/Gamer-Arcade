package com.arcade.gamerarcade.validate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {
    private static final String SECRET_KEY =
            "974912749823749823749823749237489723472394729374982374982374982374927427492"; //
    // Replace with a strong key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Generate a secure key for signing
    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Set subject
                .issuedAt(new Date()) // Set issued time
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiry time
                .signWith(key) // Sign with HMAC-SHA256
                .compact();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Validate JWT Token
    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key) // Verify signature
                    .build()
                    .parseSignedClaims(token) // Parse and verify
                    .getPayload(); // Get claims
            System.out.println(claims);
            return claims.getSubject(); // Extract username
        } catch (JwtException e) {
            log.error("INVALID JWT TOKEN");
        }
        return null;
    }

    // Check token expiration
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()  // Use parserBuilder() for parsing
                .verifyWith(key)  // Set signing key
                .build()
                .parseSignedClaims(token)  // Parse JWT
                .getPayload()
                .getExpiration();  // Extract expiration
    }

}
