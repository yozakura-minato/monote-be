package com.yozakuraMinato.monoteBe.user.repository.projection;

import java.util.UUID;

public record UserCredential(

        UUID id,
        String email,
        String hashedPassword

) {}
