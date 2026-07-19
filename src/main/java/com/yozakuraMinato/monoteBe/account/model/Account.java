package com.yozakuraMinato.monoteBe.account.model;

import com.yozakuraMinato.monoteBe.account.model.type.AccountStatus;
import com.yozakuraMinato.monoteBe.persistence.model.BaseEntity;
import com.yozakuraMinato.monoteBe.persistence.shared.PersistenceConstraint;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@DynamicUpdate
@Table(name = "accounts")
@SQLRestriction(PersistenceConstraint.SOFT_DELETE_RESTRICTION)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id",
            updatable = false)
    private UUID id;

    @Column(name = "user_id",
            nullable = false,
            updatable = false)
    private UUID userId;

    @Column(name = "name",
            length = PersistenceConstraint.Entity.SHORT_TEXT_LENGTH,
            nullable = false)
    private String name;

    @Column(name = "description",
            columnDefinition = PersistenceConstraint.Entity.LONG_TEXT)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            length = PersistenceConstraint.Entity.ENUM_LENGTH,
            nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;

    @Column(name = "balance",
            scale = PersistenceConstraint.Entity.CURRENCY_SCALE_LENGTH,
            precision = PersistenceConstraint.Entity.CURRENCY_PRECISION_LENGTH,
            nullable = false)
    private BigDecimal balance;

}
