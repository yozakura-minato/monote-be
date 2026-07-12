package com.yozakuraMinato.monoteBe.user.controller.applicationPayload;

public record SignInResponse(

        String accessToken,
        String refreshToken

) {}
