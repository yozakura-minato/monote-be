package com.yozakuraMinato.monoteBe.user.api.payload;

import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(

        @NormalizedString
        @NotNull(message = UserMessage.RefreshToken.IS_NULL)
        String refreshToken

)
{}
