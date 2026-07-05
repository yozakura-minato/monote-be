package com.yozakuraMinato.monoteBe.user.util;

import com.yozakuraMinato.monoteBe.user.controller.dto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.repository.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signUpRequestToEntity(SignUpRequest signUpRequest);

}
