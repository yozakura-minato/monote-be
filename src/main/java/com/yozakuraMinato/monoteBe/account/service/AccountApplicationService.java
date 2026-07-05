package com.yozakuraMinato.monoteBe.account.service;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AccountApplicationService {

    void createAccount(AccountMasterRequest accountMasterRequest);
    AccountMasterResponse getAccountById(UUID id);
    Page<AccountMasterResponse> getAllAccounts(Pageable pageable);
    void updateAccount(UUID id, AccountUpdateRequest accountUpdateRequest);
    void deleteAccount(UUID id);

}
