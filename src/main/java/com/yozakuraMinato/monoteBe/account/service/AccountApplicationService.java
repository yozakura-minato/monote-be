package com.yozakuraMinato.monoteBe.account.service;

import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountResponse;

public interface AccountApplicationService {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

}
