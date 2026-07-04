package com.yozakuraMinato.monoteBe.account.repository.model;

import com.yozakuraMinato.monoteBe.account.shared.type.AccountStatus;
import com.yozakuraMinato.monoteBe.persistence.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@DynamicUpdate
@Table(name = "accounts")
@SQLRestriction("is_deleted = false")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private UUID id;
    @Column(name = "userId", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private AccountStatus status;
    @Column(name = "balance", scale = 14, precision = 2, nullable = false)
    private BigDecimal balance;

}
