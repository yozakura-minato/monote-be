package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.controller.requestDto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.controller.responseDto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.responseDto.SignUpResponse;

public interface UserService {

    SignUpResponse signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);

}
