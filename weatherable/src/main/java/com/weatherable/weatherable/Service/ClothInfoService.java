package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.ClothInfoDTO;
import com.weatherable.weatherable.Model.ClothInfoEntity;
import com.weatherable.weatherable.Repository.ClothInfoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ClothInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClothInfoService.class);

    @Autowired
    ClothInfoRepository clothInfoRepository;

    public List<ClothInfoDTO> getAllClothInfoList() throws Exception {
        List<ClothInfoEntity> clothInfoEntityList = clothInfoRepository.findAll();
        if (clothInfoEntityList.isEmpty()) {
            throw new Exception("옷 정보가 없습니다.");
        }
        List<ClothInfoDTO> result = new ArrayList<>();
        for (ClothInfoEntity clothInfoEntity : clothInfoEntityList) {
            ClothInfoDTO clothInfoDTO = ClothInfoDTO.builder()
                    .id(clothInfoEntity.getId())
                    .majorCategory(clothInfoEntity.getMajor_category())
                    .middleCategory(clothInfoEntity.getMiddle_category())
                    .price(clothInfoEntity.getPrice())
                    .thickness(clothInfoEntity.getThickness())
                    .brand(clothInfoEntity.getBrand())
                    .image_path(clothInfoEntity.getBig_img())
                    .productName(clothInfoEntity.getProduct_name())
                    .season(clothInfoEntity.getSeason())
                    .build();
            result.add(clothInfoDTO);
        }
        return result;
    }

    public ClothInfoDTO getClothInfoById(String id) throws ChangeSetPersister.NotFoundException {

        if (clothInfoRepository.findById(id).isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        ClothInfoEntity clothInfoEntity = clothInfoRepository.findById(id).get();

        ClothInfoDTO result = ClothInfoDTO.builder()
                .id(clothInfoEntity.getId())
                .majorCategory(clothInfoEntity.getMajor_category())
                .middleCategory(clothInfoEntity.getMiddle_category())
                .price(clothInfoEntity.getPrice())
                .thickness(clothInfoEntity.getThickness())
                .brand(clothInfoEntity.getBrand())
                .image_path(clothInfoEntity.getBig_img())
                .productName(clothInfoEntity.getProduct_name())
                .season(clothInfoEntity.getSeason())
                .build();

        return result;
    }

}
