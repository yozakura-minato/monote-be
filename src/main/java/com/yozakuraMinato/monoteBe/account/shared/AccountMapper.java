package com.yozakuraMinato.monoteBe.account.shared;

import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountResponse;
import com.yozakuraMinato.monoteBe.account.repository.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account createAccountRequestToEntity(CreateAccountRequest createAccountRequest);
    CreateAccountResponse entityToCreateAccountResponse(Account account);

}
