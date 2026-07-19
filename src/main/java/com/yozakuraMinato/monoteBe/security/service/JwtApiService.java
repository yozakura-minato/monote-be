package com.yozakuraMinato.monoteBe.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtApiService {

    String extractUsername(String token);
    boolean validateAccessToken(String accessToken, UserDetails userDetails);

}
