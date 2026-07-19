package com.yozakuraMinato.monoteBe.user.api.payload;

public record AuthenticationResponse(

        String accessToken,
        String refreshToken

) {}
