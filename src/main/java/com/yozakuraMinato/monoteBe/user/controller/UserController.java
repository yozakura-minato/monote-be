package com.yozakuraMinato.monoteBe.user.controller;

import com.yozakuraMinato.monoteBe.common.wrapper.ApplicationResponse;
import com.yozakuraMinato.monoteBe.security.service.SecurityContextApiService;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpResponse;
import com.yozakuraMinato.monoteBe.user.service.UserApplicationService;
import com.yozakuraMinato.monoteBe.user.shared.UserMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    private final SecurityContextApiService securityContextApiService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userApplicationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApplicationResponse<SignInResponse>> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse signInResponse = userApplicationService.signIn(signInRequest);
        return ResponseEntity.ok(ApplicationResponse.data(signInResponse));
    }

}
