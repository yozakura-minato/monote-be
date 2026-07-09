package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.dto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.dto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.dto.SignUpRequest;

public interface UserApplicationService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);

}
