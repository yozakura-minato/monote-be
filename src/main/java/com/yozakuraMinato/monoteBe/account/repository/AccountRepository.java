package com.yozakuraMinato.monoteBe.account.repository;

import com.yozakuraMinato.monoteBe.account.repository.model.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByNameAndUserId(String name, UUID userId);
    Optional<AccountProjection> findProjectionByIdAndUserId(UUID id, UUID userId);
    Page<AccountProjection> findAllProjectionsByUserId(UUID userId, Pageable pageable);
    boolean existsByNameAndUserIdAndIdIsNot(String name, UUID userId, UUID id);
    Optional<Account> findByIdAndUserId(UUID id, UUID userId);

}
