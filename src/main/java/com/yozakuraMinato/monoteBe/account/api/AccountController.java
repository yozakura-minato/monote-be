package com.yozakuraMinato.monoteBe.account.api;

import com.yozakuraMinato.monoteBe.account.api.payload.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.api.payload.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.api.payload.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.service.AccountApiService;
import com.yozakuraMinato.monoteBe.common.annotation.CurrentUserId;
import com.yozakuraMinato.monoteBe.common.payload.ApiResponse;
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
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountApiService accountApiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(
            @RequestBody @Valid AccountMasterRequest accountMasterRequest, @CurrentUserId UUID userId
    ) {
        accountApiService.createAccount(accountMasterRequest, userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountMasterResponse>> getAccountById(
            @PathVariable UUID id, @CurrentUserId UUID userId
    ) {
        AccountMasterResponse accountMasterResponse = accountApiService.getAccountById(id, userId);
        return ResponseEntity.ok(ApiResponse.data(accountMasterResponse));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedModel<EntityModel<AccountMasterResponse>>>> getAllAccounts(
            PaginationRequest paginationRequest, @CurrentUserId UUID userId
    ) {
        PagedModel<EntityModel<AccountMasterResponse>> accountMasterResponseList = accountApiService
                .getAllAccounts(paginationRequest, userId);
        return ResponseEntity.ok(ApiResponse.data(accountMasterResponseList));
    }

    @PutMapping("/{id}")
    public void updateAccount(
            @PathVariable UUID id, @RequestBody @Valid AccountUpdateRequest accountUpdateRequest,
            @CurrentUserId UUID userId
    ) {
        accountApiService.updateAccount(id, accountUpdateRequest, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable UUID id, @CurrentUserId UUID userId) {
        accountApiService.deleteAccount(id, userId);
    }

}
