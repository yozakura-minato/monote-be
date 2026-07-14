package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.user.repository.UserRepository;
import com.yozakuraMinato.monoteBe.user.model.UserDetailsImplement;
import com.yozakuraMinato.monoteBe.user.repository.projection.UserDetailsProjection;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import com.yozakuraMinato.monoteBe.user.model.type.UserStatus;
import com.yozakuraMinato.monoteBe.user.service.UserDetailsModuleService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplement implements UserDetailsService, UserDetailsModuleService {

    private final UserRepository userRepository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsProjection userDetailsProjection = userRepository
                .findByEmailAndStatus(email, UserStatus.ACTIVATE)
                .orElseThrow(() -> new UsernameNotFoundException(UserMessage.Email.NOT_FOUND));

        return new UserDetailsImplement(userDetailsProjection);
    }

}
