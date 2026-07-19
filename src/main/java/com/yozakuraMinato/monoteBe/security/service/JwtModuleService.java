package com.yozakuraMinato.monoteBe.security.service;

import com.yozakuraMinato.monoteBe.user.api.payload.AuthenticationResponse;

public interface JwtModuleService {

    String generateAccessToken(String email, String userIdString);
    String generateRefreshToken(String email,  String userIdString);
    AuthenticationResponse refresh(String refreshToken);
    void revokeRefreshToken(String refreshToken, String userIdString);

}
