package com.yozakuraMinato.monoteBe.user.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE");

    private final String description;

}
