package com.weatherable.weatherable.Service;

import com.weatherable.weatherable.DTO.ClosetDTO;
import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.Repository.ClosetRepository;
import com.weatherable.weatherable.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClosetService {

    @Autowired
    ClosetRepository closetRepository;

    @Autowired
    UserRepository userRepository;


    public List<ClosetDTO> getAllClothListByUserIndex(Long userIndex) {
        Optional<List<ClosetEntity>> closetEntitiesOptional = closetRepository.retrieveAllClothByUserIndex(userIndex);
        List<ClosetDTO> result = new ArrayList<>();

        if (closetEntitiesOptional.isPresent()) {
            List<ClosetEntity> closetEntityList = closetEntitiesOptional.get();
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
        }

        return result;
    }


    public String insertCloth(ClosetDTO closetDTO) throws AccountNotFoundException {
        if (userRepository.findById(closetDTO.getUser_id()).isEmpty()) {
            throw new AccountNotFoundException("유저 없음");
        }
        UserEntity userEntity = userRepository.findById(closetDTO.getUser_id()).get();
        ClosetEntity closetEntity = ClosetEntity.builder()
                .majorCategory(closetDTO.getMajorCategory())
                .middleCategory(closetDTO.getMiddleCategory())
                .userCloset(userEntity)
                .bigImagePath(closetDTO.getBigImagePath())
                .style(closetDTO.getStyle())
                .size(closetDTO.getSize())
                .brand(closetDTO.getBrand())
                .score(closetDTO.getScore())
                .color(closetDTO.getColor())
                .smallImagePath(closetDTO.getSmallImagePath())
                .season(closetDTO.getSeason())
                .productName(closetDTO.getProductName())
                .thickness(closetDTO.getThickness())
                .price(closetDTO.getPrice())
                .active(true)
                .build();

        ClosetEntity result = closetRepository.save(closetEntity);
        return result.getProductName() + "등록완료";
    }

    public String updateCloth(Long id, ClosetDTO closetDTO) throws AccountNotFoundException {
        if (userRepository.findById(closetDTO.getUser_id()).isEmpty()) {
            throw new AccountNotFoundException("유저 없음");
        }

        UserEntity userEntity = userRepository.findById(closetDTO.getUser_id()).get();
        ClosetEntity closetEntity = ClosetEntity.builder()
                .id(id)
                .majorCategory(closetDTO.getMajorCategory())
                .middleCategory(closetDTO.getMiddleCategory())
                .userCloset(userEntity)
                .bigImagePath(closetDTO.getBigImagePath())
                .style(closetDTO.getStyle())
                .size(closetDTO.getSize())
                .brand(closetDTO.getBrand())
                .score(closetDTO.getScore())
                .color(closetDTO.getColor())
                .like(closetDTO.isLike())
                .active(true)
                .smallImagePath(closetDTO.getSmallImagePath())
                .season(closetDTO.getSeason())
                .productName(closetDTO.getProductName())
                .thickness(closetDTO.getThickness())
                .price(closetDTO.getPrice())
                .createdAt(closetDTO.getCreatedAt())
                .build();

        ClosetEntity result = closetRepository.save(closetEntity);
        return result.getProductName() + "등록완료";
    }


    public void deleteCloth(Long id) {
        closetRepository.deleteCloset(id);
    }

    public void likeCloth(Long id) {
        closetRepository.likeCloset(id);
    }

    public void unlikeCloth(Long id) {
        closetRepository.unlikeCloset(id);
    }


    public ClosetDTO retrieveClothById(Long id) throws ChangeSetPersister.NotFoundException {
        if (closetRepository.getByIdAndActive(id, true).isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        ClosetEntity closetEntity = closetRepository.getByIdAndActive(id, true).get();

        ClosetDTO closetDTO = ClosetDTO.builder()
                .id(closetEntity.getId())
                .user_id(closetEntity.getUserCloset().getId())
                .size(closetEntity.getSize())
                .style(closetEntity.getStyle())
                .middleCategory(closetEntity.getMiddleCategory())
                .price(closetEntity.getPrice())
                .season(closetEntity.getSeason())
                .smallImagePath(closetEntity.getSmallImagePath())
                .bigImagePath(closetEntity.getBigImagePath())
                .brand(closetEntity.getBrand())
                .like(closetEntity.isLike())
                .createdAt(closetEntity.getCreatedAt())
                .color(closetEntity.getColor())
                .majorCategory(closetEntity.getMajorCategory())
                .productName(closetEntity.getProductName())
                .thickness(closetEntity.getThickness())
                .userid(closetEntity.getUserCloset().getUserid())
                .build();
        return closetDTO;
    }

}
