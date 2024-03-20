package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.ClothInfoDTO;
import com.weatherable.weatherable.Model.ClothInfoEntity;
import com.weatherable.weatherable.Repository.ClothInfoRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClothInfoService {

    @Autowired
    ClothInfoRepository clothInfoRepository ;

    public List<ClothInfoDTO> getAllClothInfoList() throws Exception {
        List<ClothInfoEntity> clothInfoEntityList = clothInfoRepository.findAll();
        if (clothInfoEntityList.isEmpty()) {
            throw new Exception("저장된 옷 정보가 없습니다.");
        }
        List<ClothInfoDTO> result = new ArrayList<>();
        for (ClothInfoEntity clothInfoEntity : clothInfoEntityList) {
            ClothInfoDTO clothInfoDTO = ClothInfoDTO.builder()
                    .id(clothInfoEntity.getId())
                    .majorCategory(clothInfoEntity.getMajorCategory())
                    .middleCategory(clothInfoEntity.getMiddleCategory())
                    .price(clothInfoEntity.getPrice())
                    .thickness(clothInfoEntity.getThickness())
                    .brand(clothInfoEntity.getBrand())
                    .image_path(clothInfoEntity.getBigImagePath())
                    .productName(clothInfoEntity.getProductName())
                    .build();
            result.add(clothInfoDTO);
        }
        return result;
    }

}
