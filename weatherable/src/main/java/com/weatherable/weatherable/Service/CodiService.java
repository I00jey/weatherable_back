package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.CodiDTO;
import com.weatherable.weatherable.Entity.CodiEntity;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Repository.CodiRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public class CodiService {

    @Autowired
    CodiRepository codiRepository;

    @Autowired
    UserRepository userRepository;



    public void deleteLookbook(Long id) {
        codiRepository.deleteCodi(id);
    }

    public void createCodi(CodiDTO codiDTO) throws AccountNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUseridAndAccess(codiDTO.getUserid(), true);
        if (userEntityOptional.isEmpty()) {
            throw new AccountNotFoundException("유저 없음");
        }
        CodiEntity codiEntity = CodiEntity.builder()
                .userCodi(userEntityOptional.get())
                .topIndex(codiDTO.getTopIndex())
                .bottomIndex(codiDTO.getBottomIndex())
                .outerIndex(codiDTO.getOuterIndex())
                .shoesIndex(codiDTO.getShoesIndex())
                .accessoryIndex(codiDTO.getAccessoryIndex())
                .capIndex(codiDTO.getCapIndex())
                .build();
            codiRepository.save(codiEntity);

        }


    public void updateCodi(CodiDTO codiDTO) throws AccountNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUseridAndAccess(codiDTO.getUserid(), true);
        if (userEntityOptional.isEmpty()) {
            throw new AccountNotFoundException("유저 없음");
        }
        CodiEntity codiEntity = CodiEntity.builder()
                .id(codiDTO.getId())
                .userCodi(userEntityOptional.get())
                .topIndex(codiDTO.getTopIndex())
                .bottomIndex(codiDTO.getBottomIndex())
                .outerIndex(codiDTO.getOuterIndex())
                .shoesIndex(codiDTO.getShoesIndex())
                .accessoryIndex(codiDTO.getAccessoryIndex())
                .capIndex(codiDTO.getCapIndex())
                .build();
    }
}
