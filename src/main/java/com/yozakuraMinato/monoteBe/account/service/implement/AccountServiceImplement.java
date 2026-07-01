package com.yozakuraMinato.monoteBe.account.service.implement;

import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.controller.dto.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.repository.AccountRepository;
import com.yozakuraMinato.monoteBe.account.repository.model.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProjection;
import com.yozakuraMinato.monoteBe.account.service.AccountApplicationService;
import com.yozakuraMinato.monoteBe.account.shared.AccountMapper;
import com.yozakuraMinato.monoteBe.account.shared.AccountMessage;
import com.yozakuraMinato.monoteBe.account.shared.type.AccountStatus;
import com.yozakuraMinato.monoteBe.security.service.SecurityContextApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImplement implements AccountApplicationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SecurityContextApiService securityContextApiService;

    @Override
    public AccountMasterResponse createAccount(AccountMasterRequest accountMasterRequest) {
        UUID userId = securityContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        boolean isNameExists = accountRepository.existsByNameAndUserId(accountMasterRequest.name(), userId);
        if(isNameExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, AccountMessage.Name.alreadyExists);
        }

        Account newAccount = accountMapper.masterRequestToEntity(accountMasterRequest);
        newAccount.setUserId(userId);
        newAccount.setStatus(AccountStatus.ACTIVATE);
        newAccount.setBalance(BigDecimal.ZERO);

        Account createdAccount = accountRepository.save(newAccount);
        return accountMapper.entityToMasterResponse(createdAccount);
    }

    @Override
    public AccountMasterResponse getAccountById(UUID id) {
        UUID userId = securityContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        AccountProjection existsAccount = accountRepository
                .findProjectionByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, AccountMessage.Id.notFound));
        return accountMapper.projectionToMasterResponse(existsAccount);
    }

    @Override
    public List<AccountMasterResponse> getAllAccounts() {
        UUID userId = securityContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        List<AccountProjection> existsAccounts = accountRepository.findProjectionByUserId(userId);
        return accountMapper.projectionListToMasterResponseList(existsAccounts);
    }

    @Override
    public AccountMasterResponse updateAccount(UUID id, AccountUpdateRequest accountUpdateRequest) {
        UUID userId = securityContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        Account accountToUpdate = accountRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, AccountMessage.Id.notFound));

        boolean isNameExists = accountRepository.existsByNameAndUserIdAndIdIsNot(accountUpdateRequest.name(), userId, id);
        if(isNameExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, AccountMessage.Name.alreadyExists);
        }

        accountMapper.updateEntityFromUpdateRequest(accountUpdateRequest, accountToUpdate);
        Account updatedAccount =  accountRepository.save(accountToUpdate);
        return accountMapper.entityToMasterResponse(updatedAccount);
    }

    @Override
    public AccountMasterResponse deleteAccount(UUID id) {
        UUID userId = securityContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        Account existsAccount = accountRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, AccountMessage.Id.notFound));

        existsAccount.setDeletedAt(Instant.now());
        existsAccount.setDeletedBy(userId);
        Account deletedAccount = accountRepository.save(existsAccount);

        return accountMapper.entityToMasterResponse(deletedAccount);
    }

}
