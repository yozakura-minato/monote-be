package com.yozakuraMinato.monoteBe.user.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    USER("USER");

    private final String description;

}
