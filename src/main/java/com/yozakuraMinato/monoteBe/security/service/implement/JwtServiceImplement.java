package com.yozakuraMinato.monoteBe.security.service.implement;

import com.yozakuraMinato.monoteBe.common.exception.ResourceNotFoundException;
import com.yozakuraMinato.monoteBe.security.service.JwtApiService;
import com.yozakuraMinato.monoteBe.security.service.JwtModuleService;
import com.yozakuraMinato.monoteBe.security.service.JwtRedisApiService;
import com.yozakuraMinato.monoteBe.user.api.payload.AuthenticationResponse;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JwtServiceImplement implements JwtModuleService, JwtApiService {

    @Value("${security.jwt.key-algorithm}")
    private String keyAlgorithm;
    @Value("${security.jwt.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${security.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;
    @Value("${security.jwt.secret-key}")
    private String JWT_SECRET_KEY;

    @Autowired
    private JwtRedisApiService jwtRedisApiService;

    private String secretKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
        secretKey = Base64.getEncoder().encodeToString(
                keyGenerator.generateKey().getEncoded());
    }

    @Override
    public String generateAccessToken(String email, String userIdString) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder().claims()
                .add(claims)
                .subject(email)
                .add("uid", userIdString)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .and().signWith(getKey()).compact();
    }

    @Override
    public String generateRefreshToken(String email, String userIdString) {
        Map<String, Object> claims = new HashMap<>();
        UUID jti = UUID.ofEpochMillis(System.currentTimeMillis());

        String refreshToken = Jwts
                .builder().claims()
                .add(claims)
                .subject(email)
                .add("uid", userIdString)
                .id(jti.toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .and().signWith(getKey()).compact();

        jwtRedisApiService.saveRefreshToken(userIdString, jti.toString());
        return refreshToken;
    }

    @Override
    public AuthenticationResponse refresh(String refreshToken) {
        boolean isRefreshTokenValid = validateRefreshToken(refreshToken);
        if(!isRefreshTokenValid) throw new ResourceNotFoundException(UserMessage.RefreshToken.IS_INVALID);

        String email = extractUsername(refreshToken);
        String userIdString = extractClaim(refreshToken, "uid");
        String newAccessToken = generateAccessToken(email, userIdString);
        String newRefreshToken = generateRefreshToken(email, userIdString);
        return new AuthenticationResponse(newAccessToken, newRefreshToken);
    }

    @Override
    public void revokeRefreshToken(String refreshToken, String userIdString) {
        boolean isRefreshTokenValid = validateRefreshToken(refreshToken);
        if(!isRefreshTokenValid) throw new ResourceNotFoundException(UserMessage.RefreshToken.IS_INVALID);

        jwtRedisApiService.deleteRefreshToken(userIdString);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, "sub");
    }

    @Override
    public boolean validateAccessToken(String accessToken, UserDetails userDetails) {
        Claims claims = extractAllClaims(accessToken);
        if (claims == null) return false;

        String email = claims.getSubject();
        Date expiration = claims.getExpiration();
        String jtiString = claims.getId();

        if (email == null || email.isBlank()) return false;
        if (expiration == null || expiration.before(new Date())) return false;
        if (jtiString != null) return false;

        return email.equals(userDetails.getUsername());
    }

    private boolean validateRefreshToken(String refreshToken) {
        Claims claims = extractAllClaims(refreshToken);
        if (claims == null) return false;

        String userIdString = claims.get("uid", String.class);
        Date expiration = claims.getExpiration();
        String jtiString = claims.getId();

        if(userIdString == null || userIdString.isBlank()) return false;
        if(expiration == null || expiration.before(new Date())) return false;
        if(jtiString == null || jtiString.isBlank()) return false;

        return jwtRedisApiService.isRefreshTokenExists(userIdString, jtiString);
    }

    private String extractClaim(String token, String claimName) {
        Claims claims = extractAllClaims(token);
        if (claims == null) return null;

        return claims.get(claimName, String.class);
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException exception) {
            return null;
        }
    }

}
