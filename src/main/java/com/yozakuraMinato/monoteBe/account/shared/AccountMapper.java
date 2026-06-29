package com.yozakuraMinato.monoteBe.account.shared;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.repository.model.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProjection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account masterRequestToEntity(AccountMasterRequest accountMasterRequest);
    AccountMasterResponse entityToMasterResponse(Account account);
    AccountProjection masterRequestToProjection(AccountMasterRequest accountMasterRequest);
    AccountMasterResponse projectionToMasterResponse(AccountProjection accountProjection);
    List<AccountMasterResponse> projectionListToMasterResponseList(List<AccountProjection> accountProjectionList);

}
