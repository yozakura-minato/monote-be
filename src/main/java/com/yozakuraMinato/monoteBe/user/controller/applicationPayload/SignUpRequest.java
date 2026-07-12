package com.yozakuraMinato.monoteBe.user.controller.applicationPayload;

import com.yozakuraMinato.monoteBe.common.annotation.IsStrongPassword;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedEmail;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.user.shared.UserConstant;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @NormalizedEmail
        @NotNull(message = UserMessage.Email.IS_NULL)
        @Email(message = UserMessage.Email.IS_INVALID)
        String email,

        @NormalizedString
        @NotNull(message = UserMessage.Password.IS_NULL)
        @IsStrongPassword(
                minimalSize = UserConstant.Password.MINIMAL_SIZE, minimalLevel = UserConstant.Password.MINIMAL_LEVEL,
                message = UserMessage.Password.IS_WEAK
        )
        @Size(max = UserConstant.Password.MAXIMAL_SIZE, message = UserMessage.Password.HAS_INVALID_SIZE)
        String password,

        @NormalizedString
        @NotNull(message = UserMessage.DisplayName.IS_NULL)
        @Size(max = UserConstant.DisplayName.MAXIMAL_SIZE, message = UserMessage.DisplayName.HAS_INVALID_SIZE)
        String displayName

) {}
