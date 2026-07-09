package com.yozakuraMinato.monoteBe.account.dto;

import com.yozakuraMinato.monoteBe.account.domain.type.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountMasterResponse(

        UUID id,
        String name,
        String description,
        AccountStatus status,
        BigDecimal balance

) {}
