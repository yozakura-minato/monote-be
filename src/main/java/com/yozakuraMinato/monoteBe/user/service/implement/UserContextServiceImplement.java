package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.user.service.UserContextApiService;
import com.yozakuraMinato.monoteBe.user.domain.UserDetailsImplement;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserContextServiceImplement implements UserContextApiService {

    @Override
    public Optional<UUID> getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null
                && authentication.getPrincipal() instanceof UserDetailsImplement userDetailsImplement
        ) {
            return Optional.of(userDetailsImplement.getId());
        }

        return Optional.empty();
    }

}
