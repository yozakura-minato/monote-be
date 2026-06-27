package com.yozakuraMinato.monoteBe.user.repository.model;

import com.yozakuraMinato.monoteBe.user.repository.projection.UserDetailsProjection;
import com.yozakuraMinato.monoteBe.user.shared.type.UserRole;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UserDetailsImplement implements UserDetails {

    private final UserDetailsProjection userDetailsProjection;

    public UUID getId() {
        return userDetailsProjection.id();
    }

    public UserDetailsImplement(UserDetailsProjection userDetailsProjection) {
        this.userDetailsProjection = userDetailsProjection;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(UserRole.USER.getDescription()));
    }

    @Override
    public @Nullable String getPassword() {
        return userDetailsProjection.hashedPassword();
    }

    @Override
    public String getUsername() {
        return userDetailsProjection.email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
