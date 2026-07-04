package com.yozakuraMinato.monoteBe.account.service;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface AccountApplicationService {

    void createAccount(AccountMasterRequest accountMasterRequest);
    AccountMasterResponse getAccountById(UUID id);
    List<AccountMasterResponse> getAllAccounts();
    void updateAccount(UUID id, AccountUpdateRequest accountUpdateRequest);
    void deleteAccount(UUID id);

}
