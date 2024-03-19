package com.weatherable.weatherable.utils;

import com.weatherable.weatherable.Service.CustomUserDetailsService;
import com.weatherable.weatherable.Service.JwtUtilsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
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
                // 사용자 인증정보 담을 토큰 생성
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // 사용자 인증 세부 설정
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // securityContext는 현재 스레드의 보안 정보를 저장하는 역할을 함
                // SecurityContextHolder.getContext()로 SecurityContext에 접근하고 Authentication에 접근하여 방금 만든 토큰을 넣음
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                // SecurityContext는 해당 요청에서만 유지되고, 해당 요청의 다른 로직에서 Authentication 객체가 필요할 때 사용되다가
                // 클라이언트의 요청을 모두 처리하고 응답을 리턴하는 어느 시점에 더이상 Authentication 객체가 필요 없을 때 자동으로 삭제됨
            }
        }
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
