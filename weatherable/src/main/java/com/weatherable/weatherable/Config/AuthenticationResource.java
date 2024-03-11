package com.weatherable.weatherable.Config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


@Configuration
public class AuthenticationResource {

    @Bean
    SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        // HTTP 요청에 대한 권한 부여 적용
        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/login", "/signup", "/refresh").permitAll()
                        .anyRequest().authenticated());

        // HTTP 세션에 사용할 정책을 STATELESS로 설정하기 (REST API에서 설정해야 함.)
        // 스프링 부트 기본 옵션에서는 세션을 이용해서 로그인 로그아웃을 설정함.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        // csrf 사용 해제
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
//        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);



        // jwt
        http.oauth2ResourceServer((oauth2) -> oauth2
                .jwt(Customizer.withDefaults())
        );
        return http.build();
    }


    // 1. key pair 만들기
    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // 2. key pair 이용해서 RSA key Object 만들기
    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    // 3. JWKSource 만들기 (JSON Web Key source)
    // / Create JWKSet (a new JSON Web Key Set) with the RSA Key
    // / Create JWKSource using the JWKSet
    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        var jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
