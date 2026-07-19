package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.common.exception.ResourceConflictException;
import com.yozakuraMinato.monoteBe.security.service.JwtModuleService;
import com.yozakuraMinato.monoteBe.user.api.payload.AuthenticationResponse;
import com.yozakuraMinato.monoteBe.user.api.payload.RefreshTokenRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignInRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.User;
import com.yozakuraMinato.monoteBe.user.model.UserDetailsImplement;
import com.yozakuraMinato.monoteBe.user.model.type.UserRole;
import com.yozakuraMinato.monoteBe.user.model.type.UserStatus;
import com.yozakuraMinato.monoteBe.user.repository.UserRepository;
import com.yozakuraMinato.monoteBe.user.service.UserApiService;
import com.yozakuraMinato.monoteBe.user.shared.UserMapper;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserApiService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final JwtModuleService jwtModuleService;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        boolean isEmailConflict = userRepository.existsByEmail(signUpRequest.email());
        if(isEmailConflict) throw new ResourceConflictException(UserMessage.Email.ALREADY_EXISTS);

        User newUser = userMapper.signUpRequestToEntity(signUpRequest);
        newUser.setHashedPassword(passwordEncoder.encode(signUpRequest.password()));
        newUser.setRole(UserRole.USER);
        newUser.setStatus(UserStatus.ACTIVE);

        userRepository.save(newUser);
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password()));

        UserDetailsImplement userDetailsImplement = (UserDetailsImplement) authentication.getPrincipal();
        String accessToken = null;
        String refreshToken = null;
        if (userDetailsImplement != null) {
            accessToken = jwtModuleService.generateAccessToken(
                    userDetailsImplement.getUsername(), userDetailsImplement.getId().toString()
            );
            refreshToken = jwtModuleService.generateRefreshToken(
                    userDetailsImplement.getUsername(), userDetailsImplement.getId().toString()
            );
        }

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Override
    public AuthenticationResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        return jwtModuleService.refresh(refreshTokenRequest.refreshToken());
    }

    @Override
    public void signOut(RefreshTokenRequest refreshTokenRequest, UUID userId) {
        jwtModuleService.revokeRefreshToken(refreshTokenRequest.refreshToken(), userId.toString());
    }

}
