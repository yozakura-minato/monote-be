package com.yozakuraMinato.monoteBe.user.controller.dto;

import com.yozakuraMinato.monoteBe.shared.annotation.NormalizedEmail;
import com.yozakuraMinato.monoteBe.shared.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.user.UserMessage;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(

        @NormalizedEmail
        @NotNull(message = UserMessage.Email.isNull)
        String email,

        @NormalizedString
        @NotNull(message = UserMessage.Password.isNull)
        String password

) {}
