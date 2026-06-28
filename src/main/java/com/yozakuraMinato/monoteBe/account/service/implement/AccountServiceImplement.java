package com.yozakuraMinato.monoteBe.account.service.implement;

import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountRequest;
import com.yozakuraMinato.monoteBe.account.controller.dto.CreateAccountResponse;
import com.yozakuraMinato.monoteBe.account.repository.AccountRepository;
import com.yozakuraMinato.monoteBe.account.repository.model.Account;
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
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        UUID userId = securityContextApiService
                .getUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AccountMessage.UserId.isNull));

        boolean isNameExists = accountRepository.existsByName(createAccountRequest.name());
        if(isNameExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, AccountMessage.Name.alreadyExists);
        }

        Account newAccount = accountMapper.createAccountRequestToEntity(createAccountRequest);
        newAccount.setUserId(userId);
        newAccount.setStatus(AccountStatus.ACTIVATE);
        newAccount.setBalance(BigDecimal.ZERO);

        Account createdAccount = accountRepository.save(newAccount);
        return accountMapper.entityToCreateAccountResponse(createdAccount);
    }

}
