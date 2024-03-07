package com.weatherable.weatherable.DTO;

import com.weatherable.weatherable.enums.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClothInfoDTO {

    private long id;
    private MajorCategory majorCategory;
    private MiddleCategory middleCategory;
    private long price;
    private Color color;
    private Size size;
    private Thickness thickness;
    private String productName;
    private String brand;
    private String image_Path;
}
