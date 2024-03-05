package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.UserDTO;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

   public String insertUser(UserEntity userEntity) {
       userRepository.save(userEntity);
       return userEntity.getUserid() + "회원가입 완료";
   }

   public List<UserDTO> getAllUserList() {
       List<UserEntity> userEntityList = userRepository.findAll();

       List<UserDTO> result = new ArrayList<>();
       for(UserEntity userEntity: userEntityList) {
           UserDTO userDTO = UserDTO.builder()
                   .id(userEntity.getId())
                   .userid(userEntity.getUserid())
                   .nickname(userEntity.getNickname())
                   .password(userEntity.getPassword())
                   .image_path(userEntity.getImage_path())
                   .build();
           result.add(userDTO);
       }
       return result;
   }

}
