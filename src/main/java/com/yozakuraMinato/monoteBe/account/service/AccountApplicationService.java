package com.yozakuraMinato.monoteBe.account.service;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;

import java.util.List;
import java.util.UUID;

public interface AccountApplicationService {

    AccountMasterResponse createAccount(AccountMasterRequest accountMasterRequest);
    AccountMasterResponse getAccountById(UUID id);
    List<AccountMasterResponse> getAllAccounts();

}
