package com.yozakuraMinato.monoteBe.account.controller;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.common.annotation.CurrentUserId;
import com.yozakuraMinato.monoteBe.common.constant.CommonMessage;
import com.yozakuraMinato.monoteBe.common.annotation.HasValidPageSize;
import com.yozakuraMinato.monoteBe.common.wrapper.ApplicationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private static final String DEFAULT_SORT_FIELD = "id";

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
    public ResponseEntity<ApplicationResponse<Page<AccountMasterResponse>>> getAllAccounts(
            @HasValidPageSize(message = CommonMessage.Pageable.HAS_INVALID_SIZE) Pageable pageable,
            @CurrentUserId UUID userId
    ) {
        Pageable pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(DEFAULT_SORT_FIELD).ascending())
        );

        Page<AccountMasterResponse> accountMasterResponseList = accountApplicationService
                .getAllAccounts(pageRequest, userId);
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
