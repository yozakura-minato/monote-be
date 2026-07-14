package com.yozakuraMinato.monoteBe.user.shared;

import com.yozakuraMinato.monoteBe.user.api.payload.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signUpRequestToEntity(SignUpRequest signUpRequest);

}
