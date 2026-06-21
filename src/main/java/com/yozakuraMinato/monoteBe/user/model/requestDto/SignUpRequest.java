package com.yozakuraMinato.monoteBe.user.model.requestDto;

import com.yozakuraMinato.monoteBe.general.annotation.isPasswordStrong.IsStrongPassword;
import com.yozakuraMinato.monoteBe.general.annotation.normalizedEmail.NormalizedEmail;
import com.yozakuraMinato.monoteBe.general.annotation.normalizedString.NormalizedString;
import com.yozakuraMinato.monoteBe.user.constant.UserMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @NormalizedEmail
        @NotNull(message = UserMessage.Email.isNull)
        @Email(message = UserMessage.Email.isInvalid)
        String email,

        @NormalizedString
        @NotNull(message = UserMessage.Password.isNull)
        @Size(min = 8, max = 60, message = UserMessage.Password.hasInvalidSize)
        @IsStrongPassword(message = UserMessage.Password.isWeak)
        String password,

        @NormalizedString
        String displayName

) {}
