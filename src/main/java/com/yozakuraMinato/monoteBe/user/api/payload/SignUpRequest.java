package com.yozakuraMinato.monoteBe.user.api.payload;

import com.yozakuraMinato.monoteBe.common.annotation.IsStrongPassword;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedEmail;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.common.shared.CommonConstraint;
import com.yozakuraMinato.monoteBe.persistence.shared.PersistenceConstraint;
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
        @IsStrongPassword(minimalSize = CommonConstraint.PlainPassword.MINIMAL_SIZE,
                                minimalLevel = CommonConstraint.PlainPassword.MINIMAL_LEVEL,
                                message = UserMessage.Password.IS_WEAK)
        @Size(max = CommonConstraint.PlainPassword.MAXIMAL_SIZE,
                message = UserMessage.Password.HAS_INVALID_SIZE)
        String password,

        @NormalizedString
        @NotNull(message = UserMessage.DisplayName.IS_NULL)
        @Size(  max = PersistenceConstraint.Entity.SHORT_TEXT_LENGTH,
                message = UserMessage.DisplayName.HAS_INVALID_SIZE)
        String displayName

) {}
