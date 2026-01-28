package com.preflearn.obs.security;

import com.preflearn.obs.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.SIG.HS256;

@Service
public class JwtAuthService {

    @Value("${application.security.secret-key}")
    private CharSequence secretKey;

    @Value("${application.security.expiration}")
    private long expiration;

    public String extractUsername(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(this.getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return this.extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return this.extractUsername(token).equals(userDetails.getUsername()) && !this.isTokenExpired(token);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        User user = (User) userDetails;

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.expiration))
                .claim("fullname", user.getFirstname() + " " + user.getLastname())
                .claim("authorities", userDetails.getAuthorities())
                .signWith(this.getSigninKey(), HS256)
                .compact();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
