package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.security.service.JwtApiService;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpResponse;
import com.yozakuraMinato.monoteBe.user.repository.UserRepository;
import com.yozakuraMinato.monoteBe.user.repository.model.User;
import com.yozakuraMinato.monoteBe.user.repository.model.UserDetailsImplement;
import com.yozakuraMinato.monoteBe.user.service.UserApplicationService;
import com.yozakuraMinato.monoteBe.user.shared.UserMapper;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import com.yozakuraMinato.monoteBe.user.shared.type.UserRole;
import com.yozakuraMinato.monoteBe.user.shared.type.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserApplicationService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtApiService jwtApiService;

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        boolean isEmailExists = userRepository.existsByEmail(signUpRequest.email());
        if(isEmailExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, UserMessage.Email.alreadyExists);
        }

        User newUser = userMapper.signUpRequestToEntity(signUpRequest);
        newUser.setHashedPassword(passwordEncoder.encode(signUpRequest.password()));
        newUser.setRole(UserRole.USER);
        newUser.setStatus(UserStatus.ACTIVATE);

        User createdUser = userRepository.save(newUser);
        return userMapper.entityToSignUpResponse(createdUser);
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
