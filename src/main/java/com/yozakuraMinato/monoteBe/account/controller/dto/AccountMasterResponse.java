package com.yozakuraMinato.monoteBe.account.controller.dto;

import com.yozakuraMinato.monoteBe.account.repository.type.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountMasterResponse(

        UUID id,
        String name,
        String description,
        AccountStatus status,
        BigDecimal balance

) {}
