package com.yozakuraMinato.monoteBe.account.controller;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.shared.wrapper.ApplicationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts/")
@RequiredArgsConstructor
public class AccountController {

    private final AccountApplicationService accountApplicationService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid AccountMasterRequest accountMasterRequest) {
        accountApplicationService.createAccount(accountMasterRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse<AccountMasterResponse>> getAccountById(@PathVariable UUID id) {
        AccountMasterResponse accountMasterResponse = accountApplicationService.getAccountById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
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
    public void updateAccount(@PathVariable UUID id, @RequestBody @Valid AccountUpdateRequest accountUpdateRequest) {
        accountApplicationService.updateAccount(id, accountUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable UUID id) {
        accountApplicationService.deleteAccount(id);
    }

}
