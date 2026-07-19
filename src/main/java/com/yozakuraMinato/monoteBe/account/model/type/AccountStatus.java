package com.yozakuraMinato.monoteBe.account.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE");

    private final String description;

}
