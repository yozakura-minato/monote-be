package com.yozakuraMinato.monoteBe.user.api;

import com.yozakuraMinato.monoteBe.common.payload.ApiResponse;
import com.yozakuraMinato.monoteBe.user.api.payload.SignInRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignInResponse;
import com.yozakuraMinato.monoteBe.user.api.payload.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.service.UserApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserApiService userApiService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userApiService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<SignInResponse>> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse signInResponse = userApiService.signIn(signInRequest);
        return ResponseEntity.ok(ApiResponse.data(signInResponse));
    }

}
