package com.yozakuraMinato.monoteBe.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtApplicationService {

    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);

}
