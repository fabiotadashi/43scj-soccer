package br.com.fiap.soccer.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expireMinutes}")
    private int expireMinutes;

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        Date createdDate = Date.from(LocalDateTime.now().toInstant(OffsetDateTime.now().getOffset()));
        Date expirationDate = Date.from(LocalDateTime.now().plusMinutes(expireMinutes).toInstant(OffsetDateTime.now().getOffset()));

        return Jwts.builder()
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody()
                .getSubject();
    }


}
