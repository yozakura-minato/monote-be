package com.yozakuraMinato.monoteBe.security.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class JwtRedisRepository {

    @Value("${security.jwt.refresh-token.expiration}")
    private long REFRESH_TOKEN_EXPIRATION;
    @Value("${security.jwt.refresh-token.prefix}")
    private String REFRESH_TOKEN_PREFIX;

    private final StringRedisTemplate stringRedisTemplate;

    public void saveRefreshToken(String userIdString, String jtiString) {
        String key = REFRESH_TOKEN_PREFIX + userIdString;
        stringRedisTemplate.opsForValue().set(key, jtiString, Duration.ofMillis(REFRESH_TOKEN_EXPIRATION));
    }

    public boolean isRefreshTokenExists(String userIdString, String jtiString) {
        String key = REFRESH_TOKEN_PREFIX + userIdString;
        String existsJitString = stringRedisTemplate.opsForValue().get(key);
        return jtiString.equals(existsJitString);
    }

    public void deleteRefreshToken(String userIdString) {
        String key = REFRESH_TOKEN_PREFIX + userIdString;
        stringRedisTemplate.delete(key);
    }

}
