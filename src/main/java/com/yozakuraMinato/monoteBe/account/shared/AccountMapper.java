package com.yozakuraMinato.monoteBe.account.shared;

import com.yozakuraMinato.monoteBe.account.api.payload.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.api.payload.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.api.payload.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.model.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account masterRequestToEntity(AccountMasterRequest accountMasterRequest);

    AccountMasterResponse projectionToMasterResponse(AccountProfile accountProfile);

    void updateEntityFromUpdateRequest(AccountUpdateRequest accountUpdateRequest, @MappingTarget Account account);

}
