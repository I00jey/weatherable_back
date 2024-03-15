package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.UserDTO;
import com.weatherable.weatherable.DTO.UserForMyPageDTO;
import com.weatherable.weatherable.Entity.AuthEntity;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Repository.AuthRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import com.weatherable.weatherable.enums.Style;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthRepository authRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public String retrieveExistingPasswordById( Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if(userEntityOptional.isEmpty()) {
            throw new RuntimeException("유저 정보 없음");
        }
        String hashedPassword = userEntityOptional.get().getPassword();
        return hashedPassword;
    }

    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String insertUser(UserDTO userDTO) {
        if (userRepository.findByUserid(userDTO.getUserid()).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        String encodedPassword = encodePassword(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        UserEntity userEntity = UserEntity.builder()
                .userid(userDTO.getUserid())
                .password(userDTO.getPassword())
                .nickname(userDTO.getNickname())
                .build();

        userRepository.save(userEntity);
        authRepository.save(AuthEntity.builder()
                .usersEntity(userEntity)
                .build());
        return userEntity.getUserid() + "회원가입 완료";
    }

    public boolean isLoginInfoEqual(String userid, String password) {

        Optional<UserEntity> userEntityOptional = userRepository.findByUseridAndAccess(userid, true);
        if (userEntityOptional.isEmpty()) {
            return false;
        }
        UserEntity userEntity = userEntityOptional.get();
        String existingPassword = userEntity.getPassword();
        boolean isPasswordEqual = matchPassword(password, existingPassword);
        return isPasswordEqual;
    }

    @Transactional
    public String changeUserNickname(String nickname, Long id) {
        if (userRepository.existsByNicknameAndId(nickname, id)) {
            return "동일한 닉네임입니다.";
        }
        userRepository.updateNickname(nickname, id);
        return "닉네임 변경 완료";
    }

    @Transactional
    public String changeUserHeightAndWeight(Double height, Double weight, Long id) {
        if (userRepository.existsById(id)) {
            userRepository.updateHeightAndWeight(height, weight, id);
            return "정보 변경 완료";
        }
        return "일치하는 유저 정보가 없습니다.";
    }

    @Transactional
    public String changeUserImagePath(String imagePath, Long id) {
        if (userRepository.existsById(id)) {
            userRepository.updateImagePath(imagePath, id);
            return "프로필 변경 완료";
        }
        return "일치하는 유저 정보가 없습니다.";
    }

    @Transactional
    public String changeUserIntroduction(String introduction, Long id) {
        if (userRepository.existsById(id)) {
            userRepository.updateIntroduction(introduction, id);
            return "프로필 변경 완료";
        }
        return "일치하는 유저 정보가 없습니다.";
    }

    @Transactional
    public String changeUserPassword(String password, Long id) {
        if (userRepository.existsById(id)) {
            String encodedPassword = encodePassword(password);
            userRepository.updatePassword(encodedPassword, id);
            return "프로필 변경 완료";
        }
        return "일치하는 유저 정보가 없습니다.";
    }

    @Transactional
    public String changeUserStyle(Style style, Long id) {
        if (userRepository.existsById(id)) {
            userRepository.updateStyle(style, id);
            return "프로필 변경 완료";
        }
        return "일치하는 유저 정보가 없습니다.";
    }

    @Transactional
    public String deleteUserAccount(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User Not Founded");
        }
        String random = "del_"+UUID.randomUUID().toString().substring(1,11);
        userRepository.deleteUser(id, random);
        return "회원탈퇴 완료";
    }


    public UserForMyPageDTO getUserInfoForMyPage(String userid) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUseridAndAccess(userid, true);
        UserForMyPageDTO result;
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            result = UserForMyPageDTO.builder()
                    .id(userEntity.getId())
                    .userid(userEntity.getUserid())
                    .nickname(userEntity.getNickname())
                    .favoriteStyle(userEntity.getFavoriteStyle())
                    .height(userEntity.getHeight())
                    .weight(userEntity.getWeight())
                    .numberOfCloth(userEntity.getCloset().size())
                    .numberOfLookbook(userEntity.getLookbook().size())
                    .isPresent(true)
                    .build();
            return result;
        }

        throw new UsernameNotFoundException("일치하는 유저가 없습니다.");
    }


}
