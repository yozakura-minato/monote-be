package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.model.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignUpResponse;

public interface UserService {

    SignUpResponse signUp(SignUpRequest signUpRequest);

}
