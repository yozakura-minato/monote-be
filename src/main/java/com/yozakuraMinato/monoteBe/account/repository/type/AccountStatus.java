package com.yozakuraMinato.monoteBe.account.repository.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    INACTIVE("INACTIVE"),
    ACTIVATE("ACTIVE");

    private final String description;

}
