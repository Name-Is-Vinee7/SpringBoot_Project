package com.example.expenseTracker.security;

import com.example.expenseTracker.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

//    public String generateToken(UserEntity userEntity) {
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + expiration);
//
//        return io.jsonwebtoken.Jwts.builder()
//                .setSubject(userEntity.getUserName())
//                .claim("role", userEntity.getRole().name())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(getSecretKey())
//                .compact();
//
//    }

    public String generateToken(UserDetails userDetails) {

        Date now = new Date();

        Date expirationDate =
                new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role",
                        userDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority()
                )
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(getSecretKey())
                .compact();
    }

    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }

    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    public boolean isTokenValid(
            String token,
            String username) {

        String extractedUsername =
                extractUsername(token);

        return extractedUsername.equals(username)
                && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
