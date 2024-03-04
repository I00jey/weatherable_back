package com.weatherable.weatherable.Entity.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClothInfo {
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
