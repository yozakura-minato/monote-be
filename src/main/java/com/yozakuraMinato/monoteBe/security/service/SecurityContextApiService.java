package com.yozakuraMinato.monoteBe.security.service;

import java.util.Optional;
import java.util.UUID;

public interface SecurityContextApiService {

    Optional<UUID> getUserId();

}
