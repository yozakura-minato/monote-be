package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.controller.payload.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.payload.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.payload.SignUpRequest;

public interface UserApplicationService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);

}
