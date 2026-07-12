package com.yozakuraMinato.monoteBe.account.service;

import com.yozakuraMinato.monoteBe.account.controller.payload.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.payload.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.payload.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.common.payload.PaginationRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.UUID;

public interface AccountApplicationService {

    void createAccount(AccountMasterRequest accountMasterRequest, UUID userId);
    AccountMasterResponse getAccountById(UUID id, UUID userId);
    PagedModel<EntityModel<AccountMasterResponse>> getAllAccounts(PaginationRequest paginationRequest, UUID userId);
    void updateAccount(UUID id, AccountUpdateRequest accountUpdateRequest, UUID userId);
    void deleteAccount(UUID id, UUID userId);

}
