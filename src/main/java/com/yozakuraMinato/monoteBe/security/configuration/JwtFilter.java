package com.yozakuraMinato.monoteBe.security.configuration;

import com.yozakuraMinato.monoteBe.security.service.JwtApiService;
import com.yozakuraMinato.monoteBe.user.service.UserDetailsModuleService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Value("${security.authentication.label}")
    private String AUTHENTICATION_LABEL;
    @Value("${security.authentication.prefix}")
    private String AUTHENTICATION_PREFIX;
    @Value("${security.authentication.token-index}")
    private int TOKEN_INDEX;

    private final JwtApiService jwtApiService;
    private final ApplicationContext applicationContext;

    @Override
    @NullMarked
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(AUTHENTICATION_LABEL);
        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith(AUTHENTICATION_PREFIX)) {
            token = authHeader.substring(TOKEN_INDEX);
            email = jwtApiService.extractUsername(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext
                    .getBean(UserDetailsModuleService.class)
                    .loadUserByUsername(email);

            if (jwtApiService.validateAccessToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authToken
                        .setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
