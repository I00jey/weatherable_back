package com.weatherable.weatherable.DTO;

import com.weatherable.weatherable.Entity.UserEntity;
import com.weatherable.weatherable.enums.Style;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserForMyPageDTO {
    private long id;
    private String userid;
    private String nickname;
    private String image_path;
    private Double height;
    private Double weight;
    private Style favoriteStyle;
    private long numberOfCloth;
    private boolean isPresent;
    private UserSizeDTO userSizeDTO;

}
