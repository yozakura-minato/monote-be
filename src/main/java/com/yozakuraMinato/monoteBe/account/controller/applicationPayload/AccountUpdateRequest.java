package com.yozakuraMinato.monoteBe.account.controller.applicationPayload;

import com.yozakuraMinato.monoteBe.account.shared.AccountConstant;
import com.yozakuraMinato.monoteBe.account.shared.AccountMessage;
import com.yozakuraMinato.monoteBe.account.domain.type.AccountStatus;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountUpdateRequest(

        @NormalizedString
        @NotNull(message = AccountMessage.Name.IS_NULL)
        @Size(max = AccountConstant.Name.MAXIMAL_SIZE, message = AccountMessage.Name.HAS_INVALID_SIZE)
        String name,

        @NormalizedString
        String description,

        @NotNull(message = AccountMessage.Status.IS_NULL)
        AccountStatus status

) {}
