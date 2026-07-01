package com.yozakuraMinato.monoteBe.account.controller;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.common.wrapper.ApplicationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts/")
public class AccountController {

    @Autowired
    private AccountApplicationService accountApplicationService;

    @PostMapping("/")
    public ResponseEntity<ApplicationResponse<AccountMasterResponse>> createAccount(@RequestBody @Valid AccountMasterRequest accountMasterRequest) {
        AccountMasterResponse accountMasterResponse = accountApplicationService.createAccount(accountMasterRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApplicationResponse.data(accountMasterResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse<AccountMasterResponse>> getAccountById(@PathVariable UUID id) {
        AccountMasterResponse accountMasterResponse = accountApplicationService.getAccountById(id);
        return ResponseEntity
                .status((HttpStatus.OK))
                .body(ApplicationResponse.data(accountMasterResponse));
    }

    @GetMapping("/")
    public ResponseEntity<ApplicationResponse<List<AccountMasterResponse>>> getAllAccounts() {
        List<AccountMasterResponse> accountMasterResponseList = accountApplicationService.getAllAccounts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.data(accountMasterResponseList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse<AccountMasterResponse>> updateAccount(@PathVariable UUID id, @RequestBody @Valid AccountUpdateRequest accountUpdateRequest) {
        AccountMasterResponse accountMasterResponse = accountApplicationService.updateAccount(id, accountUpdateRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body((ApplicationResponse.data(accountMasterResponse)));
    }

}
