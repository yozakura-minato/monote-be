package com.yozakuraMinato.monoteBe.user.domain;

import com.yozakuraMinato.monoteBe.user.repository.projection.UserDetailsProjection;
import com.yozakuraMinato.monoteBe.user.domain.type.UserRole;
import org.jspecify.annotations.NullMarked;
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
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(UserRole.USER.getDescription()));
    }

    @Override
    @NullMarked
    public String getPassword() {
        return userDetailsProjection.hashedPassword();
    }

    @Override
    @NullMarked
    public String getUsername() {
        return userDetailsProjection.email();
    }

    @Override
    @NullMarked
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @NullMarked
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @NullMarked
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @NullMarked
    public boolean isEnabled() {
        return true;
    }

}
