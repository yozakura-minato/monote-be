package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.user.constant.UserMessage;
import com.yozakuraMinato.monoteBe.user.constant.type.UserRole;
import com.yozakuraMinato.monoteBe.user.constant.type.UserStatus;
import com.yozakuraMinato.monoteBe.user.model.UserMapper;
import com.yozakuraMinato.monoteBe.user.model.entity.User;
import com.yozakuraMinato.monoteBe.user.model.entity.UserPrincipal;
import com.yozakuraMinato.monoteBe.user.model.requestDto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.model.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignUpResponse;
import com.yozakuraMinato.monoteBe.user.repository.UserRepository;
import com.yozakuraMinato.monoteBe.user.service.JwtService;
import com.yozakuraMinato.monoteBe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

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
        newUser.setCreatedAt(Instant.now());
        userRepository.save(newUser);

        return userMapper.entityToSignUpResponse(newUser);
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String accessToken = null;
        if (userPrincipal != null) {
            accessToken = jwtService.generateToken(userPrincipal.getUsername());
        }
        return new SignInResponse(accessToken, null);
    }

}
