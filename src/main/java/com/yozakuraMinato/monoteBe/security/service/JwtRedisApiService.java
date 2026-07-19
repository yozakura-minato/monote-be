package com.yozakuraMinato.monoteBe.security.service;

public interface JwtRedisApiService {

    void saveRefreshToken(String userIdString, String jtiString);
    boolean isRefreshTokenExists(String userIdString, String jtiString);
    void deleteRefreshToken(String userIdString);

}
