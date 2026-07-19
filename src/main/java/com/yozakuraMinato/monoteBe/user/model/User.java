package com.yozakuraMinato.monoteBe.user.model;

import com.yozakuraMinato.monoteBe.persistence.model.BaseEntity;
import com.yozakuraMinato.monoteBe.persistence.shared.PersistenceConstraint;
import com.yozakuraMinato.monoteBe.user.model.type.UserRole;
import com.yozakuraMinato.monoteBe.user.model.type.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Setter
@Getter
@DynamicUpdate
@Table(name = "users")
@SQLRestriction(PersistenceConstraint.SOFT_DELETE_RESTRICTION)
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "display_name", length = PersistenceConstraint.Entity.SHORT_TEXT_LENGTH, nullable = false)
    private String displayName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = PersistenceConstraint.Entity.ENUM_LENGTH, nullable = false)
    private UserRole role = UserRole.ADMIN;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = PersistenceConstraint.Entity.ENUM_LENGTH, nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

}
