package com.yozakuraMinato.monoteBe.account.repository;

import com.yozakuraMinato.monoteBe.account.repository.model.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByNameAndUserId(String name, UUID userId);
    Optional<AccountProjection> findByIdAndUserId(UUID id, UUID userId);
    List<AccountProjection> findByUserId(UUID userId);

}
