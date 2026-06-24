package com.yozakuraMinato.monoteBe.user.controller;

import com.yozakuraMinato.monoteBe.user.model.requestDto.SignInRequest;
import com.yozakuraMinato.monoteBe.user.model.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignInResponse;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignUpResponse;
import com.yozakuraMinato.monoteBe.user.service.UserService;
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
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUp(signUpRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.signIn(signInRequest));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello world!");
    }

}
