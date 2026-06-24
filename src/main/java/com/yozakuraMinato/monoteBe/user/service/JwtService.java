package com.yozakuraMinato.monoteBe.user.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(String email);
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);

}
