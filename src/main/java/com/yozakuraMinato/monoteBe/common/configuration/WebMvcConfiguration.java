package com.yozakuraMinato.monoteBe.common.configuration;

import com.yozakuraMinato.monoteBe.common.annotation.resolver.CurrentUserIdResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final CurrentUserIdResolver currentUserIdResolver;

    @Override
    public void addArgumentResolvers(List resolvers) {
        resolvers.add(currentUserIdResolver);
    }

}
