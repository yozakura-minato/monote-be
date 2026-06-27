package com.yozakuraMinato.monoteBe.user.repository.model;

import com.yozakuraMinato.monoteBe.persistence.entity.BaseEntity;
import com.yozakuraMinato.monoteBe.user.shared.type.UserRole;
import com.yozakuraMinato.monoteBe.user.shared.type.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "users")
@SQLRestriction("deleted_at IS NULL")
public class User extends BaseEntity {

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

}
