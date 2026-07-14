package com.yozakuraMinato.monoteBe.account.service.implement;

import com.yozakuraMinato.monoteBe.account.shared.AccountMessage;
import com.yozakuraMinato.monoteBe.account.model.Account;
import com.yozakuraMinato.monoteBe.account.model.type.AccountStatus;
import com.yozakuraMinato.monoteBe.account.api.payload.AccountMasterRequest;
import com.yozakuraMinato.monoteBe.account.api.payload.AccountMasterResponse;
import com.yozakuraMinato.monoteBe.account.api.payload.AccountUpdateRequest;
import com.yozakuraMinato.monoteBe.account.repository.AccountRepository;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProjection;
import com.yozakuraMinato.monoteBe.account.service.AccountApiService;
import com.yozakuraMinato.monoteBe.account.shared.AccountMapper;
import com.yozakuraMinato.monoteBe.common.payload.PaginationRequest;
import com.yozakuraMinato.monoteBe.common.exception.ResourceConflictException;
import com.yozakuraMinato.monoteBe.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImplement implements AccountApiService {

    private final PagedResourcesAssembler<AccountMasterResponse> pagedResourcesAssembler;

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public void createAccount(AccountMasterRequest accountMasterRequest, UUID userId) {
        boolean isNameConflict = accountRepository.existsByNameAndUserId(accountMasterRequest.name(), userId);
        if(isNameConflict) throw new ResourceConflictException(AccountMessage.Name.ALREADY_EXISTS);

        Account newAccount = accountMapper.masterRequestToEntity(accountMasterRequest);
        newAccount.setUserId(userId);
        newAccount.setStatus(AccountStatus.ACTIVATE);
        newAccount.setBalance(BigDecimal.ZERO);

        accountRepository.save(newAccount);
    }

    @Override
    public AccountMasterResponse getAccountById(UUID id, UUID userId) {
        AccountProjection existsAccount = accountRepository
                .findProjectionByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(AccountMessage.Id.NOT_FOUND));

        return accountMapper.projectionToMasterResponse(existsAccount);
    }

    @Override
    public PagedModel<EntityModel<AccountMasterResponse>> getAllAccounts(PaginationRequest paginationRequest, UUID userId) {
        Page<AccountProjection> accountPage = accountRepository
                .findAllProjectionsByUserId(userId, paginationRequest.toPageable());

        Page<AccountMasterResponse> responsePage = accountPage.map(accountMapper::projectionToMasterResponse);
        return pagedResourcesAssembler.toModel(responsePage);
    }

    @Override
    @Transactional
    public void updateAccount(UUID id, AccountUpdateRequest accountUpdateRequest, UUID userId) {
        Account accountToUpdate = accountRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException(AccountMessage.Id.NOT_FOUND));

        if(accountUpdateRequest.name() != null && !accountUpdateRequest.name().equals(accountToUpdate.getName())) {
            boolean isNameConflict = accountRepository.existsByNameAndUserIdAndIdIsNot(
                    accountUpdateRequest.name(), userId, id
            );
            if(isNameConflict) throw new ResourceConflictException(AccountMessage.Name.ALREADY_EXISTS);
        }

        accountMapper.updateEntityFromUpdateRequest(accountUpdateRequest, accountToUpdate);
    }

    @Override
    @Transactional
    public void deleteAccount(UUID id, UUID userId) {
        boolean isAccountExists = accountRepository.existsByIdAndUserId(id, userId);
        if(!isAccountExists) throw new ResourceNotFoundException(AccountMessage.Id.NOT_FOUND);

        accountRepository.deleteByByIdAndUserId(id, userId, Instant.now(), userId);
    }

}
