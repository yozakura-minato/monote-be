package com.yozakuraMinato.monoteBe.general.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${security.public-path}")
    private String[] publicPath;

    @Value("${security.encoder-strength}")
    private int encoderStrength;

    @Autowired
    private UserDetailsService userDetailsServiceService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(publicPath).permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsServiceService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(encoderStrength));
        return daoAuthenticationProvider;
    }

}
