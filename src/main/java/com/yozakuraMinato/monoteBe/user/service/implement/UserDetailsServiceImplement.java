package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import com.yozakuraMinato.monoteBe.user.shared.type.UserStatus;
import com.yozakuraMinato.monoteBe.user.repository.model.UserDetailsImplement;
import com.yozakuraMinato.monoteBe.user.repository.projection.UserDetailsProjection;
import com.yozakuraMinato.monoteBe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsProjection userDetailsProjection = userRepository
                .findByEmailAndStatus(email, UserStatus.ACTIVATE)
                .orElseThrow(() -> new UsernameNotFoundException(UserMessage.Email.notFound));

        return new UserDetailsImplement(userDetailsProjection);
    }

}
