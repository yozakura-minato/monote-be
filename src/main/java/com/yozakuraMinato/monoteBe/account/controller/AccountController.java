package com.yozakuraMinato.monoteBe.account.controller;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.common.CommonMessage;
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

    private final AccountApplicationService accountApplicationService;
    private final String DEFAULT_SORT_FIELD = "id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid AccountMasterRequest accountMasterRequest) {
        accountApplicationService.createAccount(accountMasterRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse<AccountMasterResponse>> getAccountById(@PathVariable UUID id) {
        AccountMasterResponse accountMasterResponse = accountApplicationService.getAccountById(id);
        return ResponseEntity.ok(ApplicationResponse.data(accountMasterResponse));
    }

    @GetMapping
    public ResponseEntity<ApplicationResponse<Page<AccountMasterResponse>>> getAllAccounts(
            @HasValidPageSize(message = CommonMessage.Pageable.HAS_INVALID_SIZE)
            Pageable pageable
    ) {
        Pageable pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(DEFAULT_SORT_FIELD).ascending())
        );

        Page<AccountMasterResponse> accountMasterResponseList = accountApplicationService.getAllAccounts(pageRequest);
        return ResponseEntity.ok(ApplicationResponse.data(accountMasterResponseList));
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
