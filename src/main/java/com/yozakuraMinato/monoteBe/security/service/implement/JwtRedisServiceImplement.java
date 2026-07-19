package com.yozakuraMinato.monoteBe.security.service.implement;

import com.yozakuraMinato.monoteBe.security.service.JwtRedisApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtRedisServiceImplement implements JwtRedisApiService {

    private static final String REFRESH_TOKEN_PREFIX = "jwt:refresh:";
    @Value("${security.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveRefreshToken(String userIdString, String jtiString) {
        String key = REFRESH_TOKEN_PREFIX + userIdString;
        stringRedisTemplate.opsForValue().set(key, jtiString, Duration.ofMillis(refreshTokenExpiration));
    }

    @Override
    public boolean isRefreshTokenExists(String userIdString, String jtiString) {
        String key = REFRESH_TOKEN_PREFIX + userIdString;
        String existsJitString = stringRedisTemplate.opsForValue().get(key);
        return jtiString.equals(existsJitString);
    }

    @Override
    public void deleteRefreshToken(String userIdString) {
        String key = REFRESH_TOKEN_PREFIX + userIdString;
        stringRedisTemplate.delete(key);
    }

}
