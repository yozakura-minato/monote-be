package com.yozakuraMinato.monoteBe.persistence.configuration;

import com.yozakuraMinato.monoteBe.security.service.SecurityContextApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfiguration {

    @Autowired
    private SecurityContextApiService securityContextApiService;

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return () -> securityContextApiService.getUserId();
    }

}
