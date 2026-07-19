package com.yozakuraMinato.monoteBe.account.api.payload;

import com.yozakuraMinato.monoteBe.account.model.type.AccountStatus;
import com.yozakuraMinato.monoteBe.account.shared.AccountMessage;
import com.yozakuraMinato.monoteBe.common.annotation.NormalizedString;
import com.yozakuraMinato.monoteBe.persistence.shared.PersistenceConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountUpdateRequest(

        @NormalizedString
        @NotNull(message = AccountMessage.Name.IS_NULL)
        @Size(max = PersistenceConstraint.Entity.SHORT_TEXT_LENGTH,
                message = AccountMessage.Name.HAS_INVALID_SIZE)
        String name,

        @NormalizedString
        String description,

        @NotNull(message = AccountMessage.Status.IS_NULL)
        AccountStatus status

) {}
