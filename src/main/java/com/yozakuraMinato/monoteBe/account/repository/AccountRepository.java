package com.yozakuraMinato.monoteBe.account.repository;

import com.yozakuraMinato.monoteBe.account.model.Account;
import com.yozakuraMinato.monoteBe.account.repository.projection.AccountProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Modifying
    @Transactional
    @Query("""
        update Account a
        set a.isDeleted = true, a.updatedAt = :now, a.updatedBy = :modifierId
        where a.id = :accountId and a.userId = :authorId
    """)
    void deleteByByIdAndUserId(
            @Param("accountId") UUID id, @Param("authorId") UUID userId,
            @Param("now") Instant updatedAt, @Param("modifierId") UUID updatedBy
    );

    boolean existsByNameAndUserId(String name, UUID userId);
    boolean existsByNameAndUserIdAndIdIsNot(String name, UUID userId, UUID id);
    boolean existsByIdAndUserId(UUID id, UUID userId);

    Optional<AccountProfile> findProjectionByIdAndUserId(UUID id, UUID userId);
    Optional<Account> findByIdAndUserId(UUID id, UUID userId);

    Page<AccountProfile> findAllProjectionsByUserId(UUID userId, Pageable pageable);

}
