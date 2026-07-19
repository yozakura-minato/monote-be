package com.yozakuraMinato.monoteBe.user.api;

import com.yozakuraMinato.monoteBe.common.annotation.CurrentUserId;
import com.yozakuraMinato.monoteBe.common.payload.ApiResponse;
import com.yozakuraMinato.monoteBe.user.api.payload.AuthenticationResponse;
import com.yozakuraMinato.monoteBe.user.api.payload.RefreshTokenRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignInRequest;
import com.yozakuraMinato.monoteBe.user.api.payload.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.service.UserApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<ApiResponse<AuthenticationResponse>> signIn(@RequestBody @Valid SignInRequest signInRequest
    ) {
        AuthenticationResponse authenticationResponse = userApiService.signIn(signInRequest);
        return ResponseEntity.ok(ApiResponse.data(authenticationResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> refresh(
            @RequestBody @Valid RefreshTokenRequest refreshTokenRequest
    ) {
        AuthenticationResponse authenticationResponse = userApiService.refresh(refreshTokenRequest);
        return ResponseEntity.ok(ApiResponse.data(authenticationResponse));
    }

    @PostMapping("/sign-out")
    private void signOut(
            @RequestBody @Valid RefreshTokenRequest refreshTokenRequest, @CurrentUserId UUID userId
    ) {
        userApiService.signOut(refreshTokenRequest, userId);
    }

}
