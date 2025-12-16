package com.Server.ManageStudent.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Generate token
    public String generateToken(long id, Enum role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles","ROLE_" + role.name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // log.warn("Invalid JWT token", e);
            return false;
        }
    }

    // Get claims
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Get userId from token
    public String getUserIdFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userName = authentication.getName();
            return Long.parseLong(userName);
        }
        return null;
    }

    //Get role
    public List<String> getRolesFromToken(String token) {
        Claims claims = getClaims(token);
        Object roles = claims.get("roles");
        if (roles instanceof String) {
            return List.of(roles.toString()); // wrap String thành List
        }
        if (roles instanceof List<?>) {
            return ((List<?>) roles).stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
