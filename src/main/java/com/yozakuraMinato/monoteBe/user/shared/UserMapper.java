package com.yozakuraMinato.monoteBe.user.shared;

import com.yozakuraMinato.monoteBe.user.controller.payload.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signUpRequestToEntity(SignUpRequest signUpRequest);

}
