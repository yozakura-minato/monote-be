package com.yozakuraMinato.monoteBe.user.model.entity;

import com.yozakuraMinato.monoteBe.user.constant.type.UserRole;
import com.yozakuraMinato.monoteBe.user.constant.type.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String googleId;
    private String hashedPassword;
    private String displayName;
    private UserRole role;
    private UserStatus status;

    private Instant createdAt;
    private UUID createdBy;
    private Instant updatedAt;
    private UUID updatedBy;
    private Instant deletedAt;
    private UUID deletedBy;

}
