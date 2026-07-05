package com.yozakuraMinato.monoteBe.common.annotation.resolver;

import com.yozakuraMinato.monoteBe.common.annotation.CurrentUserId;
import com.yozakuraMinato.monoteBe.user.service.UserContextApiService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CurrentUserIdResolver implements HandlerMethodArgumentResolver {

    private final UserContextApiService userContextApiService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUserId.class)
                && methodParameter.getParameterType().equals(UUID.class);
    }

    @Override
    public Object resolveArgument(
            @NonNull MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            @NonNull NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory
    ) {
        return userContextApiService
                .getUserId()
                .orElseThrow(() -> new BadCredentialsException(null));
    }

}
