package com.yozakuraMinato.monoteBe.user.model;

import com.yozakuraMinato.monoteBe.user.model.entity.User;
import com.yozakuraMinato.monoteBe.user.model.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.model.responseDto.SignUpResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signUpRequestToEntity(SignUpRequest signUpRequest);
    SignUpResponse entityToSignUpResponse(User user);

}
