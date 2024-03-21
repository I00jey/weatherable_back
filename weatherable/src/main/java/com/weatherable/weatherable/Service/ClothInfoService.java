package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.ClothInfoDTO;
import com.weatherable.weatherable.Model.ClothInfoEntity;
import com.weatherable.weatherable.Repository.ClothInfoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClothInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClothInfoService.class);

    @Autowired
    ClothInfoRepository clothInfoRepository;

    public List<ClothInfoDTO> getAllClothInfoList() throws Exception {
        try {
            List<ClothInfoEntity> clothInfoEntityList = clothInfoRepository.findAll();
            if (clothInfoEntityList.isEmpty()) {
                LOGGER.warn("저장된 옷 정보가 없습니다.");
                return new ArrayList<>(); // 빈 리스트 반환
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
        } catch (Exception e) {
            LOGGER.error("옷 정보를 가져오는 중 오류가 발생했습니다.", e);
            throw e;
        }
    }
}
