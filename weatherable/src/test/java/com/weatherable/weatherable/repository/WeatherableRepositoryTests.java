package com.weatherable.weatherable.repository;

import com.weatherable.weatherable.Entity.AuthEntity;
import com.weatherable.weatherable.Repository.AuthRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherableRepositoryTests {


    @Autowired
    AuthRepository authRepository;



    @Test
    void oneTest() {
        List longMock = mock(List.class);
        when(longMock.size()).thenReturn(3);
        System.out.println(longMock);
        assertEquals(longMock.size(), 3);
    }

//    @Test
//    void findByUserEntityIdExistTest() {
//        Optional<AuthEntity> authEntity = authRepository.findByUserEntityId(1L);
//        System.out.println(authEntity);
//        assertTrue(authEntity.isPresent());
//    }
//
//    @Test
//    void findByUserEntityIdNotExistTest() {
//        Optional<AuthEntity> authEntity = authRepository.findByUserEntityId(999L);
//        assertTrue(authEntity.isEmpty());
//    }

}
