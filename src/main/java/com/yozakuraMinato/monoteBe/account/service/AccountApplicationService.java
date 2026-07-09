package com.yozakuraMinato.monoteBe.account.service;

import com.yozakuraMinato.monoteBe.account.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.dto.AccountUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AccountApplicationService {

    void createAccount(AccountMasterRequest accountMasterRequest, UUID userId);
    AccountMasterResponse getAccountById(UUID id, UUID userId);
    Page<AccountMasterResponse> getAllAccounts(Pageable pageable, UUID userId);
    void updateAccount(UUID id, AccountUpdateRequest accountUpdateRequest, UUID userId);
    void deleteAccount(UUID id, UUID userId);

}
