package com.yozakuraMinato.monoteBe.user.repository.model;

import com.yozakuraMinato.monoteBe.user.repository.projection.UserPrincipalProjection;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UserPrincipal implements UserDetails {

    private UserPrincipalProjection userPrincipalProjection;

    public UUID getId() {
        return userPrincipalProjection.id();
    }

    public UserPrincipal(UserPrincipalProjection userPrincipalProjection) {
        this.userPrincipalProjection = userPrincipalProjection;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return userPrincipalProjection.hashedPassword();
    }

    @Override
    public String getUsername() {
        return userPrincipalProjection.email();
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
