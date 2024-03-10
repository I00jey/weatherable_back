package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.UserDTO;
import com.weatherable.weatherable.DTO.UserForMyPageDTO;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Service.ClosetService;
import com.weatherable.weatherable.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("")
    public UserForMyPageDTO getUserByUserid(@RequestParam String userid) {
        UserForMyPageDTO result = userService.getUserInfoForMyPage(userid);
        return result;
    }

    @PatchMapping("/nickname")
    public String updateUserNickname(@RequestBody UserEntity userEntity) {
        String result = userService.changeUserNickname(userEntity.getNickname(), userEntity.getId());
        return result;
    }

    @PatchMapping("/physical")
    public String updateUserHeightAndWeight(@RequestBody UserEntity userEntity) {
        String result = userService.changeUserHeightAndWeight(userEntity.getHeight(), userEntity.getWeight(), userEntity.getId());
        return result;
    }

    @PatchMapping("/image")
    public String updateUserImagePath(@RequestBody UserEntity userEntity) {
        String result = userService.changeUserImagePath(userEntity.getImagePath(), userEntity.getId());
        return result;
    }

    @PatchMapping("/introduction")
    public String updateUserIntroduction(@RequestBody UserEntity userEntity) {
        String result = userService.changeUserIntroduction(userEntity.getIntroduction(), userEntity.getId());
        return result;
    }

    @PatchMapping("/password")
    public String updateUserPassword(@RequestBody UserEntity userEntity) {
        String existingPassword = userService.retrieveExistingPasswordById(userEntity.getId());
        boolean arePasswordsEquals = userService.matchPassword(userEntity.getPassword(), existingPassword);
        if (arePasswordsEquals) {
            return "패스워드가 동일합니다";
        }
        String result = userService.changeUserPassword(userEntity.getPassword(), userEntity.getId());
        return result;
    }

    @PatchMapping("/style")
    public String updateUserStyle(@RequestBody UserEntity userEntity) {
        String result = userService.changeUserStyle(userEntity.getFavoriteStyle(), userEntity.getId());
        return result;
    }
}
