package com.ps014.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader =
                request.getHeader("Authorization");

        // Temporary debugging messages
        System.out.println("JWT FILTER CALLED");
        System.out.println("Authorization Header: " + authorizationHeader);

        // If there is no JWT token, continue normally
        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Remove "Bearer " from the beginning
        String token = authorizationHeader.substring(7);

        try {

            // Extract email from JWT
            String email = jwtService.extractEmail(token);

            System.out.println("JWT EMAIL: " + email);

            // Create authenticated user
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            AuthorityUtils.NO_AUTHORITIES
                    );

            // Store authentication in SecurityContext
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            System.out.println("JWT AUTHENTICATION SET");

        } catch (Exception e) {

            System.out.println("JWT VALIDATION FAILED");
            System.out.println("Error: " + e.getMessage());

            SecurityContextHolder.clearContext();
        }

        // Continue to the next filter/controller
        filterChain.doFilter(request, response);
    }
}