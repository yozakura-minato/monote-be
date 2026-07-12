package com.yozakuraMinato.monoteBe.persistence.configuration;

import com.yozakuraMinato.monoteBe.user.service.UserContextModuleService;
import lombok.RequiredArgsConstructor;
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

    private final UUID SYSTEM_ID = new UUID(0L, 0L);

    private final UserContextModuleService userContextModuleService;

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> userContextModuleService
                .getUserId()
                .or(() -> Optional.of(SYSTEM_ID));
    }

}
