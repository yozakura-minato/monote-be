package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.api.payload.SignInRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignInResponse;
import com.yozakuraMinato.monoteBe.user.api.payload.SignUpRequest;

public interface UserApiService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);

}
