package com.yozakuraMinato.monoteBe.user.model;

import com.yozakuraMinato.monoteBe.user.model.type.UserRole;
import com.yozakuraMinato.monoteBe.user.repository.projection.UserCredential;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UserDetailsImplement implements UserDetails {

    private final UserCredential userCredential;

    public UserDetailsImplement(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public UUID getId() {
        return userCredential.id();
    }

    @Override
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(UserRole.USER.getDescription()));
    }

    @Override
    @NullMarked
    public String getPassword() {
        return userCredential.hashedPassword();
    }

    @Override
    @NullMarked
    public String getUsername() {
        return userCredential.email();
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
