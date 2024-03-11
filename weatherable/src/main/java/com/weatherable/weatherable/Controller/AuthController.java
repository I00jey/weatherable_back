package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.UserForMyPageDTO;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Service.AuthService;
import com.weatherable.weatherable.Service.CustomUserDetailsService;
import com.weatherable.weatherable.Service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import com.weatherable.weatherable.Service.JwtUtilsService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    JwtUtilsService jwtUtilsService;

    private JwtEncoder jwtEncoder;


    @Autowired
    public AuthController(JwtEncoder jwtEncoder) {

        this.jwtEncoder = jwtEncoder;
    }


    @PostMapping("/signup")
    public String insertUser(@RequestBody UserEntity userEntity) {

        String result = userService.insertUser(userEntity);

        return result;
    }

    @PostMapping("/login")
    public List<String> authenticate(@RequestBody UserEntity userEntity) {
        String userid = userEntity.getUserid();
        String password = userEntity.getPassword();
        boolean result = userService.isLoginInfoEqual(userid, password);
        if (!result) {
            throw new RuntimeException("아이디 혹은 비밀번호가 다릅니다.");
        }
        UserForMyPageDTO existingUserDTO = userService.getUserInfoForMyPage(userid);
        Long id = existingUserDTO.getId();
        String refreshToken = jwtUtilsService.createRefreshToken(userid);
        String accessToken = createAccessToken(userid);

        authService.updateUserRefreshToken(refreshToken, id);

        return List.of(refreshToken, accessToken);

    }

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/refresh")
    public String getRefresh(@RequestHeader("Refresh") String refreshToken) {
        boolean result = jwtUtilsService.validateToken(refreshToken);
        if(!result) {
            throw new RuntimeException("Refresh Token Is Not Valid!");
        }
        String userid = jwtUtilsService.retrieveUserid(refreshToken);
        return createAccessToken(userid);
    }



    public String createAccessToken(String userid) {
        // claims 생성
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*15))
                .subject(userid)
                .claim("scope", "ROLE_USER") // authority
                .build();

        // jwtEncoderParameters 생성하고 이걸 인코딩하고 TokenValue 추출해서 return
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

}

