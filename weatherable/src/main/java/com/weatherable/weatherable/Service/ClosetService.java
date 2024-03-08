package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Repository.ClosetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClosetService {

    @Autowired
    ClosetRepository closetRepository;

    public List<ClosetDTO> getAllClothListByUserIndex(Long userIndex) {
        List<ClosetEntity> closetEntityList = closetRepository.retrieveAllClothByUserIndex(userIndex);
        List<ClosetDTO> result = new ArrayList<>();

        for (ClosetEntity closetEntity : closetEntityList) {
            ClosetDTO closetDTO = ClosetDTO.builder()
                    .id(closetEntity.getId())
                    .productName(closetEntity.getProductName())
                    .user_id(closetEntity.getUserCloset().getId())
                    .userid(closetEntity.getUserCloset().getUserid())
                    .majorCategory(closetEntity.getMajorCategory())
                    .middleCategory(closetEntity.getMiddleCategory())
                    .price(closetEntity.getPrice())
                    .brand(closetEntity.getBrand())
                    .color(closetEntity.getColor())
                    .style(closetEntity.getStyle())
                    .size(closetEntity.getSize())
                    .season(closetEntity.getSeason())
                    .bigImagePath(closetEntity.getBigImagePath())
                    .thickness(closetEntity.getThickness())
                    .smallImagePath(closetEntity.getSmallImagePath())
                    .createdAt(closetEntity.getCreatedAt())
                    .build();
            result.add(closetDTO);
        }
            return result;
    }

  

    public String insertCloth(ClosetEntity closetEntity) {
        ClosetEntity result = closetRepository.save(closetEntity);
        return result.getProductName() + "등록완료";
    }

}
