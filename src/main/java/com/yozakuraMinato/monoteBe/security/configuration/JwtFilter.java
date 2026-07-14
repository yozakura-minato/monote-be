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

    @Value("${security.auth-header.label}")
    private String authLabel;
    @Value("${security.auth-header.start-index}")
    private int startIndex;
    @Value("${security.auth-header.start-text}")
    private String startText;

    private final JwtApiService jwtApiService;
    private final ApplicationContext applicationContext;

    @Override
    @NullMarked
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(authLabel);
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith(startText)) {
            token = authHeader.substring(startIndex);
            username = jwtApiService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext
                    .getBean(UserDetailsModuleService.class)
                    .loadUserByUsername(username);

            if (jwtApiService.validateToken(token, userDetails)) {
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
