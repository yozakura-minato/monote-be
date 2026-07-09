package com.yozakuraMinato.monoteBe.user.dto;

import com.yozakuraMinato.monoteBe.common.annotation.NormalizedEmail;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.user.constant.UserMessage;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(

        @NormalizedEmail
        @NotNull(message = UserMessage.Email.IS_NULL)
        String email,

        @NormalizedString
        @NotNull(message = UserMessage.Password.IS_NULL)
        String password

) {}
