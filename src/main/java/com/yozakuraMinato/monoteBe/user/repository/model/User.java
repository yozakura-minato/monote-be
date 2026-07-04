package com.yozakuraMinato.monoteBe.user.repository.model;

import com.yozakuraMinato.monoteBe.persistence.entity.BaseEntity;
import com.yozakuraMinato.monoteBe.user.shared.type.UserRole;
import com.yozakuraMinato.monoteBe.user.shared.type.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Entity
@Setter
@Getter
@DynamicUpdate
@Table(name = "users")
@SQLRestriction("is_deleted = false")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "google_id")
    private String googleId;
    @Column(name = "hashed_password")
    private String hashedPassword;
    @Column(name = "display_name", length = 100, nullable = false)
    private String displayName;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 50, nullable = false)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private UserStatus status;

}
