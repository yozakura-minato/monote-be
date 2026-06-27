package com.yozakuraMinato.monoteBe.user.controller;

import com.yozakuraMinato.monoteBe.common.wrapper.ApplicationResponse;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpResponse;
import com.yozakuraMinato.monoteBe.user.service.UserApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserApplicationService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApplicationResponse<SignUpResponse>> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = userService.signUp(signUpRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApplicationResponse.data(signUpResponse));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApplicationResponse<SignInResponse>> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse signInResponse = userService.signIn(signInRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.data(signInResponse));
    }

    @GetMapping("/test")
    public ResponseEntity<ApplicationResponse<String>> test() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.data("Hello world!"));
    }

}
