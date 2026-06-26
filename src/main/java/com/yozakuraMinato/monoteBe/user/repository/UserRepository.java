package com.yozakuraMinato.monoteBe.user.repository;

import com.yozakuraMinato.monoteBe.user.shared.type.UserStatus;
import com.yozakuraMinato.monoteBe.user.repository.model.User;
import com.yozakuraMinato.monoteBe.user.repository.projection.UserPrincipalProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<UserPrincipalProjection> findByEmailAndStatus(String email, UserStatus userStatus);
    boolean existsByEmail(String email);

    @Query("select u.id from User u where email = :email and status = :userStatus")
    Optional<UUID> findIdByEmailAndStatus(String email, UserStatus userStatus);

}
