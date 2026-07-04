package com.yozakuraMinato.monoteBe.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsApiService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
