package com.weatherable.weatherable.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.weatherable.weatherable.Repository.UserRepository;
import com.weatherable.weatherable.Service.CustomUserDetailsService;
import com.weatherable.weatherable.Service.JwtUtilsService;
import com.weatherable.weatherable.utils.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;
import java.util.UUID;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:env.properties")
})
public class AuthenticationResource {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtilsService jwtUtilsService;

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {

        // HTTP 요청에 대한 권한 부여 적용

        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/login", "/signup", "/refresh").permitAll()
                        .requestMatchers("/**").hasRole("USER")
                        .anyRequest().authenticated());

        // HTTP 세션에 사용할 정책을 STATELESS로 설정하기 (REST API에서 설정해야 함.)
        // 스프링 부트 기본 옵션에서는 세션을 이용해서 로그인 로그아웃을 설정함.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // csrf 사용 해제
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        // jwt

        http.addFilterBefore(new JwtRequestFilter(customUserDetailsService, jwtUtilsService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    // 1. key pair 만들기



}
