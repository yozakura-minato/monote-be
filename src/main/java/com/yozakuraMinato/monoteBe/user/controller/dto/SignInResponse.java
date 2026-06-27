package com.yozakuraMinato.monoteBe.user.controller.dto;

public record SignInResponse(

        String accessToken,
        String refreshToken

) {}
