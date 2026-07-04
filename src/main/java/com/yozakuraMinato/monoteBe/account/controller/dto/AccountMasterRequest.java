package com.yozakuraMinato.monoteBe.account.controller.dto;

import com.yozakuraMinato.monoteBe.account.AccountConstant;
import com.yozakuraMinato.monoteBe.account.AccountMessage;
import com.yozakuraMinato.monoteBe.shared.annotation.NormalizedString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountMasterRequest(

        @NormalizedString
        @NotNull(message = AccountMessage.Name.isNull)
        @Size(max = AccountConstant.Name.maximalSize, message = AccountMessage.Name.hasInvalidSize)
        String name,
        @NormalizedString
        String description

) {}
