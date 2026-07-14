package com.yozakuraMinato.monoteBe.user.api.payload;

import com.yozakuraMinato.monoteBe.common.annotation.NormalizedEmail;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(

        @NormalizedEmail
        @NotNull(message = UserMessage.Email.IS_NULL)
        String email,

        @NormalizedString
        @NotNull(message = UserMessage.Password.IS_NULL)
        String password

) {}
