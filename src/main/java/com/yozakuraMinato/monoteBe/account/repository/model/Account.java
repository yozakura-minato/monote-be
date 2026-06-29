package com.yozakuraMinato.monoteBe.account.repository.model;

import com.yozakuraMinato.monoteBe.account.shared.type.AccountStatus;
import com.yozakuraMinato.monoteBe.persistence.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "accounts")
@SQLRestriction("deleted_at IS NULL")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID userId;

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private BigDecimal balance;

}
