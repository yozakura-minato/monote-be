package com.yozakuraMinato.monoteBe.security.service.implement;

import com.yozakuraMinato.monoteBe.common.exception.ResourceNotFoundException;
import com.yozakuraMinato.monoteBe.security.repository.JwtRedisRepository;
import com.yozakuraMinato.monoteBe.security.service.JwtApiService;
import com.yozakuraMinato.monoteBe.security.service.JwtModuleService;
import com.yozakuraMinato.monoteBe.user.api.payload.AuthenticationResponse;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImplement implements JwtModuleService, JwtApiService {

    @Value("${security.jwt.secret-key}")
    private String JWT_SECRET_KEY;
    @Value("${security.jwt.access-token.expiration}")
    private long ACCESS_TOKEN_EXPIRATION;
    @Value("${security.jwt.refresh-token.expiration}")
    private long REFRESH_TOKEN_EXPIRATION;
    @Value("${security.jwt.claims.user-id}")
    private String USER_ID_CLAIM;

    private final JwtRedisRepository jwtRedisRepository;

    @Override
    public String generateAccessToken(String email, String userIdString) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder().claims()
                .add(claims)
                .subject(email)
                .add(USER_ID_CLAIM, userIdString)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .and().signWith(getJwtSecretKey()).compact();
    }

    @Override
    public String generateRefreshToken(String email, String userIdString) {
        Map<String, Object> claims = new HashMap<>();
        UUID jti = UUID.ofEpochMillis(System.currentTimeMillis());

        String refreshToken = Jwts
                .builder().claims()
                .add(claims)
                .subject(email)
                .add(USER_ID_CLAIM, userIdString)
                .id(jti.toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .and().signWith(getJwtSecretKey()).compact();

        jwtRedisRepository.saveRefreshToken(userIdString, jti.toString());
        return refreshToken;
    }

    @Override
    public AuthenticationResponse refresh(String refreshToken) {
        boolean isRefreshTokenValid = validateRefreshToken(refreshToken);
        if(!isRefreshTokenValid) throw new ResourceNotFoundException(UserMessage.RefreshToken.IS_INVALID);

        Claims claims = extractAllClaims(refreshToken);
        if (claims == null) return null;

        String email = claims.getSubject();
        String userIdString = claims.get(USER_ID_CLAIM, String.class);
        String newAccessToken = generateAccessToken(email, userIdString);
        String newRefreshToken = generateRefreshToken(email, userIdString);

        return new AuthenticationResponse(newAccessToken, newRefreshToken);
    }

    @Override
    public void revokeRefreshToken(String refreshToken, String userIdString) {
        boolean isRefreshTokenValid = validateRefreshToken(refreshToken);
        if(!isRefreshTokenValid) throw new ResourceNotFoundException(UserMessage.RefreshToken.IS_INVALID);

        jwtRedisRepository.deleteRefreshToken(userIdString);
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        if (claims == null) return null;

        return claims.getSubject();
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

        String userIdString = claims.get(USER_ID_CLAIM, String.class);
        Date expiration = claims.getExpiration();
        String jtiString = claims.getId();

        if(userIdString == null || userIdString.isBlank()) return false;
        if(expiration == null || expiration.before(new Date())) return false;
        if(jtiString == null || jtiString.isBlank()) return false;

        return jwtRedisRepository.isRefreshTokenExists(userIdString, jtiString);
    }

    private SecretKey getJwtSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getJwtSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException exception) {
            return null;
        }
    }

}
