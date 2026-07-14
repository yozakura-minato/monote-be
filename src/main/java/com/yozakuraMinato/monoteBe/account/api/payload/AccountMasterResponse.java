package com.yozakuraMinato.monoteBe.account.api.payload;

import com.yozakuraMinato.monoteBe.account.model.type.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountMasterResponse(

        UUID id,
        String name,
        String description,
        AccountStatus status,
        BigDecimal balance

) {}
