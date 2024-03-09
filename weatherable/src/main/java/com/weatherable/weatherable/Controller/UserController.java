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

    @PostMapping("")
    public String insertUser(@RequestBody UserEntity userEntity) {
        String result = userService.insertUser(userEntity);
        return result;
    }


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
}
