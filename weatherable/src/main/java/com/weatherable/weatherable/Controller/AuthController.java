package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.UserDTO;
import com.weatherable.weatherable.DTO.UserForMyPageDTO;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Service.AuthService;
import com.weatherable.weatherable.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

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
        String refreshToken = createRefreshToken(userid);
        String accessToken = createAccessToken(userid);

        authService.updateUserRefreshToken(refreshToken, id);

        return List.of(refreshToken, accessToken);

    }

//    @PostMapping("/refresh")
//    public List<String> authenticate(@RequestBody UserEntity userEntity) {
//        String userid = userEntity.getUserid();
//        String password = userEntity.getPassword();
//        boolean result = userService.isLoginInfoEqual(userid, password);
//        if (!result) {
//            throw new RuntimeException("아이디 혹은 비밀번호가 다릅니다.");
//        }
//        return List.of(createRefreshToken(userid), createAccessToken(userid));
//
//    }

    public String createAccessToken(String userid) {
        // claims 생성
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 30))
                .subject(userid)
                .claim("scope", "ROLE_USER") // authority
                .build();

        // jwtEncoderParameters 생성하고 이걸 인코딩하고 TokenValue 추출해서 return
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    public String createRefreshToken(String userid) {
        // claims 생성
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 60))
                .subject(userid)
                .claim("scope", "Refresh") // authority
                .build();

        // jwtEncoderParameters 생성하고 이걸 인코딩하고 TokenValue 추출해서 return
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameters).getTokenValue();
    }

    private Object createScope(Authentication authentication) {
        // 권한 받기
        return authentication.getAuthorities().stream() // list of Authority objects
                .map(a -> a.getAuthority()) // list of String
                .collect(Collectors.joining(" "));
    }
}

record JwtResponse(String token) {
}