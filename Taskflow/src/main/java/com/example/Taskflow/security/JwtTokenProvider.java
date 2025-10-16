package com.example.Taskflow.security;


import com.example.Taskflow.model.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    private Key key;

    private Key getSigningKey() {
        if (this.key == null) {

            this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        }
        return this.key;
    }

    public String generateToken(User user) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException ex) {
            log.error("Token JWT invalid: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Token JWT expirat: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Token JWT nesuportat: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Claim-uri JWT goale: {}", ex.getMessage());
        }
        return false;
    }

    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}