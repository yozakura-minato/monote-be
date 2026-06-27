package com.yozakuraMinato.monoteBe.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;
    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private Instant updatedAt;
    @LastModifiedBy
    @Column(insertable = false)
    private UUID updatedBy;

    private Instant deletedAt;
    private UUID deletedBy;

}
