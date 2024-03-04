package com.weatherable.weatherable.DTO;

import com.weatherable.weatherable.Entity.enums.ClothInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalClothDTO {
    private String id;
    private ClothInfo clothInfo;
}
