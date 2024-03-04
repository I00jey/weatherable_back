package com.weatherable.weatherable.Controller;

import com.weatherable.weatherable.DTO.UserDTO;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Service.ClosetService;
import com.weatherable.weatherable.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public String insertUser(@RequestBody UserEntity userEntity) {
        String result = userService.insertUser(userEntity);
        return result;
    }

    @GetMapping("/user")
    @ResponseBody
    public List<UserDTO> getAllUserList() {
        List<UserDTO> userDTOList = userService.getAllUserList();
        return userDTOList;
    }
}
