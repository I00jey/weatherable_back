package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.CodiDTO;
import com.weatherable.weatherable.Entity.CodiEntity;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Repository.CodiRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CodiService {

    @Autowired
    CodiRepository codiRepository;

    @Autowired
    UserRepository userRepository;


    public void deleteCodi(Long id) {
        codiRepository.deleteCodi(id);
    }

    public void createCodi(CodiDTO codiDTO) throws AccountNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUseridAndActive(codiDTO.getUserid(), true);
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
        Optional<UserEntity> userEntityOptional = userRepository.findByUseridAndActive(codiDTO.getUserid(), true);
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
        codiRepository.save(codiEntity);
    }

    public List<CodiDTO> retrieveAllCodi() throws Exception {
        Optional<List<CodiEntity>> codiEntityOptional = codiRepository.findByActiveOrderByCreatedAtDesc(true);
        if (codiEntityOptional.isEmpty()) {
            throw new Exception("불러올 코디가 없습니다.");
        }
        List<CodiEntity> codiEntitityList = codiEntityOptional.get();
        List<CodiDTO> codiDTOList = new ArrayList<>();
        for (var codiEntity : codiEntitityList) {
            var codiDTo = CodiDTO.builder()
                    .id(codiEntity.getId())
                    .createdAt(codiEntity.getCreatedAt())
                    .userid(codiEntity.getUserCodi().getUserid())
                    .nickname(codiEntity.getUserCodi().getNickname())
                    .user_id(codiEntity.getUserCodi().getId())
                    .topIndex(codiEntity.getTopIndex())
                    .bottomIndex(codiEntity.getBottomIndex())
                    .outerIndex(codiEntity.getOuterIndex())
                    .shoesIndex(codiEntity.getShoesIndex())
                    .accessoryIndex(codiEntity.getAccessoryIndex())
                    .capIndex(codiEntity.getCapIndex())
                    .build();
        codiDTOList.add(codiDTo);
        }
        return codiDTOList;

    }

    public List<CodiDTO> retrieveSomeOnesCodi(Long user_id) throws Exception {
        Optional<List<CodiEntity>> codiEntityOptional =
                codiRepository.findByUserCodiIdAndActiveAndShowingOrderByCreatedAtDesc(user_id, true, true);
        if (codiEntityOptional.isEmpty()) {
            throw new Exception("불러올 코디가 없습니다.");
        }
            List<CodiDTO> codiDTOList = new ArrayList<>();
            List<CodiEntity> codiEntitityList = codiEntityOptional.get();
            for (var codiEntity : codiEntitityList) {
                var codiDTo = CodiDTO.builder()
                        .id(codiEntity.getId())
                        .createdAt(codiEntity.getCreatedAt())
                        .userid(codiEntity.getUserCodi().getUserid())
                        .nickname(codiEntity.getUserCodi().getNickname())
                        .user_id(codiEntity.getUserCodi().getId())
                        .topIndex(codiEntity.getTopIndex())
                        .bottomIndex(codiEntity.getBottomIndex())
                        .outerIndex(codiEntity.getOuterIndex())
                        .shoesIndex(codiEntity.getShoesIndex())
                        .accessoryIndex(codiEntity.getAccessoryIndex())
                        .capIndex(codiEntity.getCapIndex())
                        .build();
                codiDTOList.add(codiDTo);
            }
            return codiDTOList;
    }

    public List<CodiDTO> retrieveMyCodi(Long user_id) throws Exception {
        Optional<List<CodiEntity>> codiEntityOptional =
                codiRepository.findByUserCodiIdAndActiveOrderByCreatedAtDesc(user_id, true);
        if (codiEntityOptional.isEmpty()) {
            throw new Exception("불러올 코디가 없습니다.");
        }
        List<CodiEntity> codiEntitityList = codiEntityOptional.get();
        List<CodiDTO> codiDTOList = new ArrayList<>();
        for (var codiEntity : codiEntitityList) {
            var codiDTo = CodiDTO.builder()
                    .id(codiEntity.getId())
                    .createdAt(codiEntity.getCreatedAt())
                    .userid(codiEntity.getUserCodi().getUserid())
                    .nickname(codiEntity.getUserCodi().getNickname())
                    .user_id(codiEntity.getUserCodi().getId())
                    .topIndex(codiEntity.getTopIndex())
                    .bottomIndex(codiEntity.getBottomIndex())
                    .outerIndex(codiEntity.getOuterIndex())
                    .shoesIndex(codiEntity.getShoesIndex())
                    .accessoryIndex(codiEntity.getAccessoryIndex())
                    .capIndex(codiEntity.getCapIndex())
                    .build();
            codiDTOList.add(codiDTo);
        }
        return codiDTOList;
    }

    public CodiDTO retrieveSingleCodi(Long id) throws Exception {
        Optional<CodiEntity> codiEntityOptional =
                codiRepository.getByIdAndActive(id, true);
        if (codiEntityOptional.isEmpty()) {
            throw new Exception("불러올 코디가 없습니다.");
        }
        CodiEntity codiEntity = codiEntityOptional.get();
        CodiDTO codiDTO = CodiDTO.builder()
                .id(codiEntity.getId())
                .createdAt(codiEntity.getCreatedAt())
                .userid(codiEntity.getUserCodi().getUserid())
                .nickname(codiEntity.getUserCodi().getNickname())
                .user_id(codiEntity.getUserCodi().getId())
                .topIndex(codiEntity.getTopIndex())
                .bottomIndex(codiEntity.getBottomIndex())
                .outerIndex(codiEntity.getOuterIndex())
                .shoesIndex(codiEntity.getShoesIndex())
                .accessoryIndex(codiEntity.getAccessoryIndex())
                .capIndex(codiEntity.getCapIndex())
                .build();
        return codiDTO;
    }

}




