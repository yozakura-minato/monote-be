package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.common.exception.ResourceConflictException;
import com.yozakuraMinato.monoteBe.security.service.JwtApiService;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.repository.UserRepository;
import com.yozakuraMinato.monoteBe.user.repository.model.User;
import com.yozakuraMinato.monoteBe.user.repository.model.UserDetailsImplement;
import com.yozakuraMinato.monoteBe.user.service.UserApplicationService;
import com.yozakuraMinato.monoteBe.user.util.UserMapper;
import com.yozakuraMinato.monoteBe.user.constant.UserMessage;
import com.yozakuraMinato.monoteBe.user.type.UserRole;
import com.yozakuraMinato.monoteBe.user.type.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserApplicationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final JwtApiService jwtApiService;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        boolean isEmailConflict = userRepository.existsByEmail(signUpRequest.email());
        if(isEmailConflict) throw new ResourceConflictException(UserMessage.Email.ALREADY_EXISTS);

        User newUser = userMapper.signUpRequestToEntity(signUpRequest);
        newUser.setHashedPassword(passwordEncoder.encode(signUpRequest.password()));
        newUser.setRole(UserRole.USER);
        newUser.setStatus(UserStatus.ACTIVATE);

        userRepository.save(newUser);
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password()));

        UserDetailsImplement userDetailsImplement = (UserDetailsImplement) authentication.getPrincipal();
        String accessToken = null;
        if (userDetailsImplement != null) {
            accessToken = jwtApiService.generateToken(userDetailsImplement.getUsername());
        }

        return new SignInResponse(accessToken, null);
    }

}
