package com.yozakuraMinato.monoteBe.user.api.payload;

public record SignInResponse(

        String accessToken,
        String refreshToken

) {}
