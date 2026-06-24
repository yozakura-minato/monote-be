package com.yozakuraMinato.monoteBe.user.model.entity;

import com.yozakuraMinato.monoteBe.user.constant.type.UserRole;
import com.yozakuraMinato.monoteBe.user.constant.type.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "users")
@SQLRestriction("deleted_at IS NULL")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String googleId;
    private String hashedPassword;
    private String displayName;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Instant createdAt;
    private UUID createdBy;
    private Instant updatedAt;
    private UUID updatedBy;
    private Instant deletedAt;
    private UUID deletedBy;

}
