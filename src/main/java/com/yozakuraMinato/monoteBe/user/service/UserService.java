package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.model.requestDto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.model.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignUpResponse;

public interface UserService {

    SignUpResponse signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);

}
