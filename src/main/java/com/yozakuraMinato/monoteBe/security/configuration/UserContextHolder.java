package com.yozakuraMinato.monoteBe.security.configuration;

import com.yozakuraMinato.monoteBe.user.repository.model.UserDetailsImplement;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserContextHolder {

    public Optional<UUID> getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetailsImplement userDetailsImplement) {
            return Optional.of(userDetailsImplement.getId());
        }

        return Optional.empty();
    }

}
