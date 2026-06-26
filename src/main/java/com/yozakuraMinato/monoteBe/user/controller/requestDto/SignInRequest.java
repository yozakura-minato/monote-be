package com.yozakuraMinato.monoteBe.user.controller.requestDto;

import com.yozakuraMinato.monoteBe.common.annotation.NormalizedEmail;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(

        @NormalizedEmail
        @NotNull(message = UserMessage.Email.isNull)
        String email,

        @NormalizedString
        @NotNull(message = UserMessage.Password.isNull)
        String password

) {}
