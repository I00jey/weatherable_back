package com.weatherable.weatherable.repository;

import com.weatherable.weatherable.Entity.AuthEntity;
import com.weatherable.weatherable.Repository.AuthRepository;
import com.weatherable.weatherable.Repository.ClosetRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRepositoryTests {


    @Autowired
    AuthRepository authRepository;

    @Autowired
    ClosetRepository closetRepository;

    @Autowired
    UserRepository userRepository;




    @Transactional
    @Test
    void changeUserProfileImage() {
        userRepository.updateImagePath("aaaaaa", 1L);
        var userEntityOptional = userRepository.findByUserid("aaaa");
        assertEquals(userEntityOptional.get().getImagePath(), "aaaaaa");
    }



}
