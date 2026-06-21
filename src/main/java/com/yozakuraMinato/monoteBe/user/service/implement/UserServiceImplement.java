package com.yozakuraMinato.monoteBe.user.service.implement;

import com.yozakuraMinato.monoteBe.user.constant.UserMessage;
import com.yozakuraMinato.monoteBe.user.constant.type.UserRole;
import com.yozakuraMinato.monoteBe.user.constant.type.UserStatus;
import com.yozakuraMinato.monoteBe.user.model.UserMapper;
import com.yozakuraMinato.monoteBe.user.model.entity.User;
import com.yozakuraMinato.monoteBe.user.model.entity.UserPrincipal;
import com.yozakuraMinato.monoteBe.user.model.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignUpResponse;
import com.yozakuraMinato.monoteBe.user.repository.UserRepository;
import com.yozakuraMinato.monoteBe.user.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserServiceImplement implements UserService, UserDetailsService {

    @Value("${security.encoder-strength}")
    private int encoderStrength;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    void bCryptPasswordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(encoderStrength);
    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findById(UUID.fromString(username))
                .filter(thisUser ->
                        UserStatus.ACTIVATE.equals(thisUser.getStatus())
                        && thisUser.getDeletedAt() == null)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserMessage.Id.notFound));

        return new UserPrincipal(user);
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        boolean isEmailExists = userRepository.existsByEmailAndDeletedAtIsNull(signUpRequest.email());
        if(isEmailExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, UserMessage.Email.alreadyExists);
        }

        User newUser = userMapper.requestToEntity(signUpRequest);
        newUser.setHashedPassword(bCryptPasswordEncoder.encode(signUpRequest.password()));
        newUser.setRole(UserRole.USER);
        newUser.setStatus(UserStatus.INACTIVE);
        newUser.setCreatedAt(Instant.now());
        userRepository.save(newUser);

        return userMapper.entityToResponse(newUser);
    }

}
