package com.weatherable.weatherable.DTO;

import com.weatherable.weatherable.Entity.UserEntity;
import lombok.Builder;


public class OAuth2UserInfo{

    String username;
    String password;

    public OAuth2UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
