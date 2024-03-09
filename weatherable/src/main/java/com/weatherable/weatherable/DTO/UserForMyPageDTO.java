package com.weatherable.weatherable.DTO;

import com.weatherable.weatherable.enums.Style;
import lombok.Builder;
import lombok.Data;

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
    private long numberOfLookbook;
    private boolean isPresent;

}
