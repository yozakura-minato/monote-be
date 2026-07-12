package com.yozakuraMinato.monoteBe.account.controller;

import com.yozakuraMinato.monoteBe.account.controller.payload.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.payload.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.payload.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.common.annotation.CurrentUserId;
import com.yozakuraMinato.monoteBe.common.payload.ApplicationResponse;
import com.yozakuraMinato.monoteBe.common.payload.PaginationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountApplicationService accountApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(
            @RequestBody @Valid AccountMasterRequest accountMasterRequest, @CurrentUserId UUID userId
    ) {
        accountApplicationService.createAccount(accountMasterRequest, userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse<AccountMasterResponse>> getAccountById(
            @PathVariable UUID id, @CurrentUserId UUID userId
    ) {
        AccountMasterResponse accountMasterResponse = accountApplicationService.getAccountById(id, userId);
        return ResponseEntity.ok(ApplicationResponse.data(accountMasterResponse));
    }

    @GetMapping
    public ResponseEntity<ApplicationResponse<PagedModel<EntityModel<AccountMasterResponse>>>> getAllAccounts(
            PaginationRequest paginationRequest, @CurrentUserId UUID userId
    ) {
        PagedModel<EntityModel<AccountMasterResponse>> accountMasterResponseList = accountApplicationService
                .getAllAccounts(paginationRequest, userId);
        return ResponseEntity.ok(ApplicationResponse.data(accountMasterResponseList));
    }

    @PutMapping("/{id}")
    public void updateAccount(
            @PathVariable UUID id, @RequestBody @Valid AccountUpdateRequest accountUpdateRequest,
            @CurrentUserId UUID userId
    ) {
        accountApplicationService.updateAccount(id, accountUpdateRequest, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable UUID id, @CurrentUserId UUID userId) {
        accountApplicationService.deleteAccount(id, userId);
    }

}
