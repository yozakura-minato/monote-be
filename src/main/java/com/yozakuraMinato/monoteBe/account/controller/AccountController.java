package com.yozakuraMinato.monoteBe.account.controller;

import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountResponse;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.common.wrapper.ApplicationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/")
public class AccountController {

    @Autowired
    private AccountApplicationService accountApplicationService;

    @PostMapping("/create")
    public ResponseEntity<ApplicationResponse<CreateAccountResponse>> createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        CreateAccountResponse createAccountResponse = accountApplicationService.createAccount(createAccountRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApplicationResponse.data(createAccountResponse));
    }

}
