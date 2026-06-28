package com.yozakuraMinato.monoteBe.account.controller.dto;

import com.yozakuraMinato.monoteBe.account.shared.type.AccountStatus;

import java.math.BigDecimal;

public record CreateAccountResponse (

        Long id,
        String name,
        String description,
        AccountStatus status,
        BigDecimal balance

) {}
