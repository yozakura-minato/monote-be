package com.yozakuraMinato.monoteBe.user.controller.payload;

public record SignInResponse(

        String accessToken,
        String refreshToken

) {}
