package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.UserDTO;
import com.weatherable.weatherable.DTO.UserForMyPageDTO;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String insertUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity.getUserid() + "회원가입 완료";
    }

    @Transactional
    public String changeUserNickname(String nickname, Long id) {
        if ( userRepository.existsByNicknameAndId(nickname, id)) {
            return "동일한 닉네임입니다.";
        }
        userRepository.updateNickname(nickname, id);
        return "닉네임 변경 완료";
    }



    public UserForMyPageDTO getUserInfoForMyPage(String userid) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserid(userid);
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

        result = UserForMyPageDTO.builder()
                .isPresent(false)
                .build();
        return result;
    }


}
