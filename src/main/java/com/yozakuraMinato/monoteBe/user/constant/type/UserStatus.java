package com.yozakuraMinato.monoteBe.user.constant.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    INACTIVE("INACTIVE"),
    ACTIVATE("ACTIVE");

    private final String description;

}
