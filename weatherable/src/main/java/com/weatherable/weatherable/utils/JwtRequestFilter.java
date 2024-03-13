package com.weatherable.weatherable.utils;

import com.weatherable.weatherable.Service.CustomUserDetailsService;
import com.weatherable.weatherable.Service.JwtUtilsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtilsService jwtUtilsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String accessToken = request.getHeader("Authorization");

        String username = null;
        if(accessToken != null) {
            username = jwtUtilsService.getUsername(accessToken);
            String issuer = jwtUtilsService.getIssuer(accessToken);
            if (!issuer.equals("access")) {
                throw new RuntimeException("Not Access Token Exception");
            }
        }
        try {
        if(username!=null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if(jwtUtilsService.validateAccessToken(accessToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
