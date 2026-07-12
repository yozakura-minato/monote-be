package com.yozakuraMinato.monoteBe.user.service;

import com.yozakuraMinato.monoteBe.user.controller.applicationPayload.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.applicationPayload.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.applicationPayload.SignUpRequest;

public interface UserApplicationService {

    void signUp(SignUpRequest signUpRequest);
    SignInResponse signIn(SignInRequest signInRequest);

}
