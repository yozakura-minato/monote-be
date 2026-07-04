package com.yozakuraMinato.monoteBe.user.controller.dto;

import com.yozakuraMinato.monoteBe.shared.annotation.IsStrongPassword;
import com.yozakuraMinato.monoteBe.shared.annotation.NormalizedEmail;
import com.yozakuraMinato.monoteBe.shared.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.user.UserConstant;
import com.yozakuraMinato.monoteBe.user.UserMessage;
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
        @Size(min = UserConstant.Password.minimalSize, max = UserConstant.Password.maximalSize, message = UserMessage.Password.hasInvalidSize)
        @IsStrongPassword(message = UserMessage.Password.isWeak)
        String password,

        @NormalizedString
        @NotNull(message = UserMessage.DisplayName.isNull)
        @Size(max = UserConstant.DisplayName.maximalSize, message = UserMessage.DisplayName.hasInvalidSize)
        String displayName

) {}
