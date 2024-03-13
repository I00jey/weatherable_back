package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.Entity.AuthEntity;
import com.weatherable.weatherable.Entity.CustomUserDetails;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private final CustomUserDetails customUserDetails;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, CustomUserDetails customUserDetails) {
        this.userRepository = userRepository;
        this.customUserDetails = customUserDetails;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserid(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        CustomUserDetails userDetails = new CustomUserDetails(user.getUserid(), user.getPassword());
        return userDetails;


    }



}
