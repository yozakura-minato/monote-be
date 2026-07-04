package com.yozakuraMinato.monoteBe.persistence.configuration;

import com.yozakuraMinato.monoteBe.security.service.SecurityContextApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class AuditConfiguration {

    private final SecurityContextApiService securityContextApiService;

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return securityContextApiService::getUserId;
    }

}
