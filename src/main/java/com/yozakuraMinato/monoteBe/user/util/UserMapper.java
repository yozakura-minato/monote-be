package com.yozakuraMinato.monoteBe.user.util;

import com.yozakuraMinato.monoteBe.user.dto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signUpRequestToEntity(SignUpRequest signUpRequest);

}
