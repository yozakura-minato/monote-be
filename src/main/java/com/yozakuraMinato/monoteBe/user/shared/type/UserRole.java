package com.yozakuraMinato.monoteBe.user.shared.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    USER("USER");

    private final String description;

}
