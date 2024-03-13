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
    public String updateUserNickname(@RequestBody UserDTO userDTO) {
        String result = userService.changeUserNickname(userDTO.getNickname(), userDTO.getId());
        return result;
    }

    @PatchMapping("/physical")
    public String updateUserHeightAndWeight(@RequestBody UserDTO userDTO) {
        String result = userService.changeUserHeightAndWeight(userDTO.getHeight(), userDTO.getWeight(), userDTO.getId());
        return result;
    }

    @PatchMapping("/image")
    public String updateUserImagePath(@RequestBody UserDTO userDTO) {
        String result = userService.changeUserImagePath(userDTO.getImagePath(), userDTO.getId());
        return result;
    }

    @PatchMapping("/introduction")
    public String updateUserIntroduction(@RequestBody UserDTO userDTO) {
        String result = userService.changeUserIntroduction(userDTO.getIntroduction(), userDTO.getId());
        return result;
    }

    @PatchMapping("/password")
    public String updateUserPassword(@RequestBody UserDTO userDTO) {
        String existingPassword = userService.retrieveExistingPasswordById(userDTO.getId());
        boolean arePasswordsEquals = userService.matchPassword(userDTO.getPassword(), existingPassword);
        if (arePasswordsEquals) {
            return "패스워드가 동일합니다";
        }
        String result = userService.changeUserPassword(userDTO.getPassword(), userDTO.getId());
        return result;
    }

    @PatchMapping("/style")
    public String updateUserStyle(@RequestBody UserDTO userDTO) {
        String result = userService.changeUserStyle(userDTO.getFavoriteStyle(), userDTO.getId());
        return result;
    }
}
