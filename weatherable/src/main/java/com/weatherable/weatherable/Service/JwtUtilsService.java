package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.AuthDTO;
import com.weatherable.weatherable.Repository.AuthRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.sql.Date;
import java.time.Instant;

@Service
public class JwtUtilsService {

    private final KeyPair keyPair; // RSA key pair
    @Autowired
    AuthRepository authRepository;

    @Autowired
    public JwtUtilsService(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    SecretKey key = Keys
            .secretKeyFor(SignatureAlgorithm.HS256);

    // 토큰에서 클레임을 추출하는 함수
    public Claims extractAllClaims(String token) {
        var jwtSubject =  Jwts.parserBuilder().setSigningKey(key).build();
        var parseClaims = jwtSubject.parseClaimsJws(token).getBody();
        return parseClaims;
    }

    public String createRefreshToken(String userid) {
        // claims 생성
        var claims = Jwts.builder()
                .setIssuer("refresh")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(60*60*24*14)))
                .setSubject(userid)
                .claim("scope", "Refresh") // authority
                .signWith(key)
                .compact();

        return claims;
    }

    public boolean validateToken(String token) {
        var claims = extractAllClaims(token);
        String userid = claims.getSubject();
        String existingRefreshToken = getExistingRefreshToken(userid);
        boolean isValidToken = token.equals(existingRefreshToken);
        boolean isExpired = isTokenExpired(token);

        return !isExpired && isValidToken;
    }

    public String getExistingRefreshToken(String userid) {
        var userEntityOptional = authRepository.findByUserEntityUserid(userid);
        if(userEntityOptional.isEmpty()) {
            return "유저 없음";
        }

        return userEntityOptional.get().getRefreshToken();
    }

    public boolean isTokenExpired(String token) {
        var claims = extractAllClaims(token);
        var expiration = claims.getExpiration();
        return expiration.before(Date.from(Instant.now()));
    }

    public String retrieveUserid(String token) {
        var claims = extractAllClaims(token);
        return claims.getSubject();
    }
    // 특정 클레임을 추출하는 함수
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    // 토큰의 만료 날짜 확인
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    // 토큰이 만료되었는지 확인
//    public Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    // 토큰의 유효성 검사
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    // 토큰에서 사용자명 추출
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

}
