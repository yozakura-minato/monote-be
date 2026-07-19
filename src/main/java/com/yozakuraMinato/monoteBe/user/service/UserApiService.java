package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.api.payload.AuthenticationResponse;
import com.yozakuraMinato.monoteBe.user.api.payload.RefreshTokenRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignInRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignUpRequest;

import java.util.UUID;

public interface UserApiService {

    void signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
    AuthenticationResponse refresh(RefreshTokenRequest refreshTokenRequest);
    void signOut(RefreshTokenRequest refreshTokenRequest, UUID userId);

}
