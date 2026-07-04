package com.yozakuraMinato.monoteBe.account.service.implement;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.repository.AccountRepository;
import com.yozakuraMinato.monoteBe.account.repository.model.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProjection;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.account.service.mapper.AccountMapper;
import com.yozakuraMinato.monoteBe.account.AccountMessage;
import com.yozakuraMinato.monoteBe.account.repository.type.AccountStatus;
import com.yozakuraMinato.monoteBe.shared.exception.custom.ResourceConflictException;
import com.yozakuraMinato.monoteBe.shared.exception.custom.ResourceNotFoundException;
import com.yozakuraMinato.monoteBe.user.service.UserContextApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImplement implements AccountApplicationService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final UserContextApiService userContextApiService;

    @Override
    public void createAccount(AccountMasterRequest accountMasterRequest) {
        UUID userId = userContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        boolean isNameConflict = accountRepository.existsByNameAndUserId(accountMasterRequest.name(), userId);
        if(isNameConflict) throw new ResourceNotFoundException(AccountMessage.Name.alreadyExists);

        Account newAccount = accountMapper.masterRequestToEntity(accountMasterRequest);
        newAccount.setUserId(userId);
        newAccount.setStatus(AccountStatus.ACTIVATE);
        newAccount.setBalance(BigDecimal.ZERO);
        accountRepository.save(newAccount);
    }

    @Override
    public AccountMasterResponse getAccountById(UUID id) {
        UUID userId = userContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        AccountProjection existsAccount = accountRepository
                .findProjectionByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(AccountMessage.Id.notFound));
        return accountMapper.projectionToMasterResponse(existsAccount);
    }

    @Override
    public List<AccountMasterResponse> getAllAccounts() {
        UUID userId = userContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        List<AccountProjection> existsAccounts = accountRepository.findProjectionByUserId(userId);
        return accountMapper.projectionListToMasterResponseList(existsAccounts);
    }

    @Override
    @Transactional
    public void updateAccount(UUID id, AccountUpdateRequest accountUpdateRequest) {
        UUID userId = userContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        Account accountToUpdate = accountRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(AccountMessage.Id.notFound));

        if(accountUpdateRequest.name() != null && !accountUpdateRequest.name().equals(accountToUpdate.getName())) {
            boolean isNameConflict = accountRepository.existsByNameAndUserIdAndIdIsNot(accountUpdateRequest.name(), userId, id);
            if(isNameConflict) throw new ResourceConflictException(AccountMessage.Name.alreadyExists);
        }

        accountMapper.updateEntityFromUpdateRequest(accountUpdateRequest, accountToUpdate);
    }

    @Override
    @Transactional
    public void deleteAccount(UUID id) {
        UUID userId = userContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));
        accountRepository
                .findByIdAndUserId(id, userId)
                .ifPresentOrElse(
                        account -> account.setDeleted(true),
                        () -> { throw new ResourceNotFoundException(AccountMessage.Id.notFound); }
                );
    }

}
