package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.controller.dto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpRequest;

public interface UserApplicationService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);

}
