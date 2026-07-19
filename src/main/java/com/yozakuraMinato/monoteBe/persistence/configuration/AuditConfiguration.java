package com.yozakuraMinato.monoteBe.persistence.configuration;

import com.yozakuraMinato.monoteBe.user.service.UserContextModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class AuditConfiguration {

    @Value("${persistence.system-id}")
    private String SYSTEM_ID_STRING;

    private final UserContextModuleService userContextModuleService;

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> userContextModuleService
                .getUserId()
                .or(() -> Optional.of(UUID.fromString(SYSTEM_ID_STRING)));
    }

}
