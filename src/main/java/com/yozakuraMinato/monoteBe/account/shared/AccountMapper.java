package com.yozakuraMinato.monoteBe.account.shared;

import com.yozakuraMinato.monoteBe.account.controller.payload.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.payload.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.payload.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.domain.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account masterRequestToEntity(AccountMasterRequest accountMasterRequest);

    AccountMasterResponse projectionToMasterResponse(AccountProjection accountProjection);

    void updateEntityFromUpdateRequest(AccountUpdateRequest accountUpdateRequest, @MappingTarget Account account);

}
