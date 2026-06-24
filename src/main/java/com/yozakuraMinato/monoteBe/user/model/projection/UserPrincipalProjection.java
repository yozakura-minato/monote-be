package com.yozakuraMinato.monoteBe.user.model.projection;

import java.util.UUID;

public record UserPrincipalProjection(

        UUID id,
        String email,
        String hashedPassword

) {}
