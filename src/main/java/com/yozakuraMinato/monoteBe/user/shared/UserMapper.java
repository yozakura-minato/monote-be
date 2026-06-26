package com.yozakuraMinato.monoteBe.user.shared;

import com.yozakuraMinato.monoteBe.user.repository.model.User;
import com.yozakuraMinato.monoteBe.user.controller.requestDto.SignUpRequest;
import com.yozakuraMinato.monoteBe.user.controller.responseDto.SignUpResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signUpRequestToEntity(SignUpRequest signUpRequest);
    SignUpResponse entityToSignUpResponse(User user);

}
