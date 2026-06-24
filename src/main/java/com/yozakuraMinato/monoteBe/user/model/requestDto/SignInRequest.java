package com.yozakuraMinato.monoteBe.user.model.requestDto;

import com.yozakuraMinato.monoteBe.general.annotation.normalizedEmail.NormalizedEmail;
import com.yozakuraMinato.monoteBe.general.annotation.normalizedString.NormalizedString;
import com.yozakuraMinato.monoteBe.user.constant.UserMessage;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(

        @NormalizedEmail
        @NotNull(message = UserMessage.Email.isNull)
        String email,

        @NormalizedString
        @NotNull(message = UserMessage.Password.isNull)
        String password

) {}
