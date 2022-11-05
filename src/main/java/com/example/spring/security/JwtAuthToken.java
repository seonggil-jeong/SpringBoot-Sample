package com.example.spring.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class JwtAuthToken implements AuthToken<Claims> {
    private final String token;
    private final Key key;

    public JwtAuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    public JwtAuthToken(String id, Key key, String role, Map<String, Object> claims, Date expiredDate) {
        this.key = key;
        this.token = createJwtAuthToken(id, role, claims, expiredDate).get();
    }


    public String getToken() {
        return this.token;
    }

    public Optional<String> createJwtAuthToken(String id, String role, Map<String, Object> claimsMap, Date expiredDate) {
        Claims claims = new DefaultClaims(claimsMap);
        claims.put(JwtAuthToken.AUTHORITIES_KEY, role);

        return Optional.ofNullable(Jwts.builder()
                .setSubject(id)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate).compact());
    }




    @Override
    public boolean validate() {
        return getData() != null;
    }

    @Override
    public Claims getData() {

        try {
            return Jwts.parserBuilder().setSigningKey(key.getEncoded()).build().parseClaimsJws(token).getBody();
        } catch (SecurityException e) {
            log.warn("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.warn("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.warn("JWT token compact of handler are invalid");
        }


        return null;
    }
}
