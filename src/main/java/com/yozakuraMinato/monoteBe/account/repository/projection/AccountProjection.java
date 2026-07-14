package com.yozakuraMinato.monoteBe.account.repository.projection;

import com.yozakuraMinato.monoteBe.account.model.type.AccountStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record AccountProjection (

        UUID id,
        String name,
        String description,
        AccountStatus status,
        BigDecimal balance

) {}
