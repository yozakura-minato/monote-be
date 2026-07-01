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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    private final SecurityContextApiService securityContextApiService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApplicationResponse<SignUpResponse>> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = userApplicationService.signUp(signUpRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApplicationResponse.data(signUpResponse));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApplicationResponse<SignInResponse>> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse signInResponse = userApplicationService.signIn(signInRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.data(signInResponse));
    }

    @GetMapping("/test")
    public ResponseEntity<ApplicationResponse<UUID>> test() {
        UUID userId = securityContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserMessage.Id.notFound));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.data(userId));
    }

}
